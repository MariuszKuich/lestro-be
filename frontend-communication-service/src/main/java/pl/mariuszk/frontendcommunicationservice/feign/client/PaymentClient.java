package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("payment-service")
public interface PaymentClient {

}
