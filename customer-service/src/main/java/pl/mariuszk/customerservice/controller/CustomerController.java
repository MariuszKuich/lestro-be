package pl.mariuszk.customerservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mariuszk.customerservice.model.frontend.SignUpDto;
import pl.mariuszk.customerservice.service.CustomerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/sign-up")
    public void createNewAccount(@Valid @RequestBody SignUpDto signUpDto) {
        customerService.createNewAccount(signUpDto);
    }
}
