package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mariuszk.customerservice.model.frontend.AddressDto;
import pl.mariuszk.customerservice.model.frontend.SavedAddressDto;
import pl.mariuszk.customerservice.model.frontend.SignUpDto;

@FeignClient("customer-service")
public interface CustomerClient {

    @PostMapping("/customer/sign-up")
    void createNewAccount(@RequestBody SignUpDto signUpDto);

    @PostMapping("/customer/save-address")
    void saveAddressData(@RequestBody AddressDto addressDto);

    @GetMapping("/customer/get-address")
    SavedAddressDto getAddressData(@RequestParam String customerMail);
}
