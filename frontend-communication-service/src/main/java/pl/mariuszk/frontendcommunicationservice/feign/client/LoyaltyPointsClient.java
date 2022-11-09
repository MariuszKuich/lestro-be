package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("loyalty-points-service")
public interface LoyaltyPointsClient {

}
