package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("customer-service")
public interface CustomerClient {

}
