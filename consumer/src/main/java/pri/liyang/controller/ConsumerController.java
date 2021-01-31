package pri.liyang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pri.liyang.service.ConsumerService;

@RestController
@RequestMapping("/consume")
public class ConsumerController {

    @Autowired
    @Qualifier("consumerService")
    ConsumerService consumerService;

    @GetMapping("/grab")
    public String consumerCallBusiness(Integer orderId, Integer driverId) {
        // 远程调用抢单的方法
        String result = consumerService.grabOrder(orderId, driverId);

        return "抢单结果：" + result;
    }

}
