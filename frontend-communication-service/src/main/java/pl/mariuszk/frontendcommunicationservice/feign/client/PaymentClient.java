package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("payment-service")
public interface PaymentClient {

    @GetMapping("/payment/status")
    String getStatus();
}
