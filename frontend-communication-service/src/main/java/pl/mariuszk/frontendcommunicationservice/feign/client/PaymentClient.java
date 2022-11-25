package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.mariuszk.paymentservice.model.frontend.NewPaymentDataDto;
import pl.mariuszk.paymentservice.model.frontend.PaymentDto;

import java.util.List;

@FeignClient("payment-service")
public interface PaymentClient {

    @GetMapping("/payment/all")
    List<PaymentDto> getAvailablePaymentMethods();

    @PostMapping("/payment/new")
    String sendNewPaymentToPaymentServiceAndObtainRedirectUrl(@RequestBody NewPaymentDataDto newPaymentDataDto);
}
