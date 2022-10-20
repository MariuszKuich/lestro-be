package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("delivery-service")
public interface DeliveryClient {

    @GetMapping("/delivery/status")
    String getStatus();
}
