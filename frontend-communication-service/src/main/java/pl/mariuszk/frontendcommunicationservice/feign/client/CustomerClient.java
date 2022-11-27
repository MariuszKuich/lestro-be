package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.mariuszk.customerservice.model.frontend.SignUpDto;

@FeignClient("customer-service")
public interface CustomerClient {

    @PostMapping("/customer/sign-up")
    void createNewAccount(@RequestBody SignUpDto signUpDto);
}
