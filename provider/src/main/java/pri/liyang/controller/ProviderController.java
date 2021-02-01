package pri.liyang.controller;

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

    @Override
    public String grabOrder(Integer orderId, Integer driverId) {

        // 查询订单状态
        Order order = orderService.queryById(orderId);

        try {
            // 模拟网络延迟，多线程导致数据错乱
            Thread.sleep(300 + new Random().nextInt(300));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
    }
}
