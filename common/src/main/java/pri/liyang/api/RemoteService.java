package pri.liyang.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "/remote")
public interface RemoteService {

    @GetMapping("/grab")
    String grabOrder(@RequestParam("orderId") Integer orderId, @RequestParam("driverId") Integer driverId);

}
