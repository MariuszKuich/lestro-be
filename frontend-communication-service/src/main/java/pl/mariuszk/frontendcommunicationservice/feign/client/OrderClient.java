package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("order-service")
public interface OrderClient {

    @GetMapping("/order/status")
    String getStatus();
}
