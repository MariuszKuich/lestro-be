package pl.mariuszk.paymentservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mariuszk.paymentservice.model.frontend.NewPaymentDataDto;
import pl.mariuszk.paymentservice.model.frontend.PaymentDto;
import pl.mariuszk.paymentservice.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/all")
    public List<PaymentDto> getAvailablePaymentMethods() {
        return paymentService.getAvailablePaymentMethods();
    }

    @PostMapping("/new")
    public String sendNewPaymentToPaymentServiceAndObtainRedirectUrl(@RequestBody NewPaymentDataDto newPaymentDataDto) {
        return paymentService.sendNewPaymentToPaymentServiceAndObtainRedirectUrl(newPaymentDataDto);
    }

    @PostMapping("/save-payment/{orderNumber}")
    public ResponseEntity<Void> savePayment(@PathVariable long orderNumber) {
        try {
            paymentService.savePaymentForOrder(orderNumber);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Saving payment for order {} failed: {}", orderNumber, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
