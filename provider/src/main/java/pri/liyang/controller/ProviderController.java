package pri.liyang.controller;

import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pri.liyang.api.RemoteService;
import pri.liyang.entity.Order;
import pri.liyang.service.OrderService;

import java.util.Random;

@RestController
public class ProviderController implements RemoteService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public String grabOrder(Integer orderId, Integer driverId) {
        // 一个订单就是一个字符串key的锁
        String lockKey = ("order_" + orderId).intern();

        // 根据订单字符串，拿到一台Redis的分布式锁
        RLock rLock = redissonClient.getLock(lockKey);

        // 将Redis分布式锁，集成到Redisson红锁中，这里可以放多个RLock
        // 也即是多个Redis实例来完成红锁分布式锁
        RedissonRedLock rrLock = new RedissonRedLock(rLock);

        try {
            // 分布式红锁加锁
            rrLock.lock();
            // TODO 下面写加锁后的业务逻辑

            // 查询订单状态
            Order order = orderService.queryById(orderId);
            // 模拟网络延迟，多线程导致数据错乱
            Thread.sleep(300 + new Random().nextInt(300));

            // 是否抢到订单
            boolean isGrabbed = false;

            // 如果未被抢，则抢，然后更新状态
            if (order.getOrderState() == 0) {
                order.setOrderState(1);
                orderService.update(order);
                System.out.println("司机" + driverId + " 抢到了订单" + order.getOrderId() + " ，恭喜！");
                isGrabbed = true;
            } else {
                System.out.println("司机" + driverId + " 未抢到订单" + order.getOrderId() + " ，遗憾！");
                isGrabbed = false;
            }

            return isGrabbed? "抢到了" : "未抢到";

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            // 最后记得在finally块中释放Redisson分布式红锁
            rrLock.unlock();
        }

        return "服务器内部错误";
    }
}
