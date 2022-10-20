package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("loyalty-points-service")
public interface LoyaltyPointsClient {

    @GetMapping("/loyalty-points/status")
    String getStatus();
}
