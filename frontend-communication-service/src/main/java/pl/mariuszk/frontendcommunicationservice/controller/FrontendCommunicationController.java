package pl.mariuszk.frontendcommunicationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mariuszk.deliveryservice.model.frontend.DeliveryDto;
import pl.mariuszk.frontendcommunicationservice.feign.client.DeliveryClient;
import pl.mariuszk.frontendcommunicationservice.feign.client.PaymentClient;
import pl.mariuszk.frontendcommunicationservice.feign.client.ProductClient;
import pl.mariuszk.paymentservice.model.frontend.PaymentDto;
import pl.mariuszk.productservice.model.frontend.ProductDetailsDto;
import pl.mariuszk.productservice.model.frontend.ProductDto;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lestro-be")
@RequiredArgsConstructor
public class FrontendCommunicationController {

    private final ProductClient productClient;
    private final DeliveryClient deliveryClient;
    private final PaymentClient paymentClient;

    @GetMapping("/product/list")
    public ResponseEntity<Page<ProductDto>> getProductsList(@RequestParam Map<String, String> request) {
        return ResponseEntity.ok(productClient.getProductsList(request));
    }

    @GetMapping("/product/available-plants")
    public ResponseEntity<List<String>> getAvailablePlants() {
        return ResponseEntity.ok(productClient.getAvailablePlants());
    }

    @GetMapping("/product/{code}")
    public ResponseEntity<ProductDetailsDto> getProductDetails(@PathVariable("code") String code) {
        return productClient.getProductDetails(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/delivery/all")
    public ResponseEntity<List<DeliveryDto>> getAvailableDeliveryMethods() {
        return ResponseEntity.ok(deliveryClient.getAvailableDeliveryMethods());
    }

    @GetMapping("/payment/all")
    public ResponseEntity<List<PaymentDto>> getAvailablePaymentMethods() {
        return ResponseEntity.ok(paymentClient.getAvailablePaymentMethods());
    }
}
