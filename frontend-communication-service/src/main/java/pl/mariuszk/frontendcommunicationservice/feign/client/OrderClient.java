package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("order-service")
public interface OrderClient {

}
