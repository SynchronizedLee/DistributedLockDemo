package pri.liyang.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import pri.liyang.api.RemoteService;

@FeignClient(name = "provider")
@Qualifier("consumerService")
public interface ConsumerService extends RemoteService {

}
