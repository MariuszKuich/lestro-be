package pl.mariuszk.paymentservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mariuszk.paymentservice.model.frontend.PaymentDto;
import pl.mariuszk.paymentservice.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/all")
    public List<PaymentDto> getAvailablePaymentMethods() {
        return paymentService.getAvailablePaymentMethods();
    }
}
