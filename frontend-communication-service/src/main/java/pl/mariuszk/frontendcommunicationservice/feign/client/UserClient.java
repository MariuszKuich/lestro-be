package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("user-service")
public interface UserClient {

}
