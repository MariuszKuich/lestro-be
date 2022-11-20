package pl.mariuszk.frontendcommunicationservice.controller;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mariuszk.deliveryservice.model.frontend.DeliveryDto;
import pl.mariuszk.elasticsearch.enums.CompositionElemType;
import pl.mariuszk.frontendcommunicationservice.feign.client.DeliveryClient;
import pl.mariuszk.frontendcommunicationservice.feign.client.OrderClient;
import pl.mariuszk.frontendcommunicationservice.feign.client.PaymentClient;
import pl.mariuszk.frontendcommunicationservice.feign.client.ProductClient;
import pl.mariuszk.orderservice.model.frontend.order.OrderDto;
import pl.mariuszk.paymentservice.model.frontend.PaymentDto;
import pl.mariuszk.productservice.model.frontend.CompositionElementDto;
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
    private final OrderClient orderClient;

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

    @GetMapping("/product/configurator/options")
    public ResponseEntity<Map<CompositionElemType, List<CompositionElementDto>>> getAvailableConfiguratorOptions() {
        return ResponseEntity.ok(productClient.getAvailableConfiguratorOptions());
    }

    @GetMapping("/delivery/all")
    public ResponseEntity<List<DeliveryDto>> getAvailableDeliveryMethods() {
        return ResponseEntity.ok(deliveryClient.getAvailableDeliveryMethods());
    }

    @GetMapping("/payment/all")
    public ResponseEntity<List<PaymentDto>> getAvailablePaymentMethods() {
        return ResponseEntity.ok(paymentClient.getAvailablePaymentMethods());
    }

    @PostMapping("/order/new")
    public ResponseEntity<String> createNewOrder(@RequestBody OrderDto orderDto) {
        try {
            long orderNumber = orderClient.createNewOrder(orderDto);
            return ResponseEntity.ok(Long.toString(orderNumber));
        } catch (FeignException.BadRequest e) {
            return ResponseEntity.badRequest().body("Bad request - invalid data");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }
}
