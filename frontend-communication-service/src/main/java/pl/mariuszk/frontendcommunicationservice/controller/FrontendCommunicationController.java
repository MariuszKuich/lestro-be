package pl.mariuszk.frontendcommunicationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mariuszk.frontendcommunicationservice.feign.client.*;

@RestController
@RequestMapping("/lestro-be")
@RequiredArgsConstructor
public class FrontendCommunicationController {

    @Value("${server.port}")
    private int serverPort;

    private final ProductClient productClient;
    private final PricingClient pricingClient;
    private final DeliveryClient deliveryClient;
    private final PaymentClient paymentClient;
    private final CustomerClient customerClient;
    private final LoyaltyPointsClient loyaltyPointsClient;
    private final UserClient userClient;

    @GetMapping("/backend-status")
    public ResponseEntity<String> getBackendStatus() {
        String statusMessage = String.format("FrontendCommunicationService: RUNNING (port %s)\n", serverPort) +
                productClient.getStatus() + "\n" +
                pricingClient.getStatus() + "\n" +
                deliveryClient.getStatus() + "\n" +
                paymentClient.getStatus() + "\n" +
                customerClient.getStatus() + "\n" +
                loyaltyPointsClient.getStatus() + "\n" +
                userClient.getStatus();

        return ResponseEntity.ok(statusMessage);
    }
}
