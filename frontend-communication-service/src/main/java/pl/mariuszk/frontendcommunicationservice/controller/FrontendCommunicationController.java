package pl.mariuszk.frontendcommunicationservice.controller;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.mariuszk.customerservice.model.frontend.SignUpDto;
import pl.mariuszk.deliveryservice.model.frontend.DeliveryDto;
import pl.mariuszk.common.enums.CompositionElemType;
import pl.mariuszk.frontendcommunicationservice.feign.client.*;
import pl.mariuszk.frontendcommunicationservice.model.frontend.RedirectDto;
import pl.mariuszk.frontendcommunicationservice.service.security.TokenService;
import pl.mariuszk.orderservice.model.frontend.order.OrderDto;
import pl.mariuszk.paymentservice.model.frontend.NewPaymentDataDto;
import pl.mariuszk.paymentservice.model.frontend.PaymentDto;
import pl.mariuszk.productservice.model.frontend.CompositionElementDto;
import pl.mariuszk.productservice.model.frontend.ProductDetailsDto;
import pl.mariuszk.productservice.model.frontend.ProductDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lestro-be")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class FrontendCommunicationController {

    private final ProductClient productClient;
    private final DeliveryClient deliveryClient;
    private final PaymentClient paymentClient;
    private final OrderClient orderClient;
    private final CustomerClient customerClient;
    private final TokenService tokenService;

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
        } catch (FeignException.NotFound e) {
            return ResponseEntity.badRequest().body("Bad request - product with given code not found");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }

    @GetMapping("/payment/save-and-redirect/{orderNumber}")
    public ResponseEntity<RedirectDto> savePaymentAndRedirectToPaymentService(@PathVariable long orderNumber) {
        BigDecimal orderValue = orderClient.getTotalOrderValue(orderNumber);
        NewPaymentDataDto newPaymentData = NewPaymentDataDto.builder()
                .orderValue(orderValue)
                .orderNumber(orderNumber)
                .build();
        RedirectDto redirectDto =
                new RedirectDto(paymentClient.sendNewPaymentToPaymentServiceAndObtainRedirectUrl(newPaymentData));
        return ResponseEntity.ok(redirectDto);
    }

    @PostMapping("/customer/sign-up")
    public ResponseEntity<Void> createNewAccount(@RequestBody SignUpDto signUpDto) {
        try {
            customerClient.createNewAccount(signUpDto);
            return ResponseEntity.ok().build();
        } catch (FeignException.BadRequest e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (FeignException.Conflict e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateAndReturnJwtToken(Authentication authentication) {
        return ResponseEntity.ok(tokenService.generateToken(authentication));
    }
}
