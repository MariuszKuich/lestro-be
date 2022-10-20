package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("pricing-service")
public interface PricingClient {

    @GetMapping("/pricing/status")
    String getStatus();
}
