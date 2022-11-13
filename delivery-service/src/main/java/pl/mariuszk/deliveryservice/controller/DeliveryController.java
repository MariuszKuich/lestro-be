package pl.mariuszk.deliveryservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mariuszk.deliveryservice.model.frontend.DeliveryDto;
import pl.mariuszk.deliveryservice.service.DeliveryService;

import java.util.List;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/all")
    public List<DeliveryDto> getAvailableDeliveryMethods() {
        return deliveryService.getAvailableDeliveryMethods();
    }
}
