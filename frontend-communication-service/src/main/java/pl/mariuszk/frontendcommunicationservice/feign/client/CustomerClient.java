package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("customer-service")
public interface CustomerClient {

    @GetMapping("/customer/status")
    String getStatus();
}
