package pl.mariuszk.customerservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mariuszk.customerservice.model.frontend.AddressDto;
import pl.mariuszk.customerservice.model.frontend.SavedAddressDto;
import pl.mariuszk.customerservice.model.frontend.SignUpDto;
import pl.mariuszk.customerservice.service.AddressDataService;
import pl.mariuszk.customerservice.service.CustomerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final AddressDataService addressDataService;

    @PostMapping("/sign-up")
    public void createNewAccount(@Valid @RequestBody SignUpDto signUpDto) {
        customerService.createNewAccount(signUpDto);
    }

    @PostMapping("/save-address")
    public void saveAddressData(@RequestBody AddressDto addressDto) {
        addressDataService.saveAddressData(addressDto);
    }

    @GetMapping("/get-address")
    public SavedAddressDto getAddressData(@RequestParam String customerMail) {
        return addressDataService.getAddressData(customerMail);
    }
}
