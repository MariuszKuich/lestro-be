package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mariuszk.paymentservice.model.frontend.PaymentDto;

import java.util.List;

@FeignClient("payment-service")
public interface PaymentClient {

    @GetMapping("/payment/all")
    List<PaymentDto> getAvailablePaymentMethods();
}
