package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mariuszk.deliveryservice.model.frontend.DeliveryDto;

import java.util.List;

@FeignClient("delivery-service")
public interface DeliveryClient {

    @GetMapping("/delivery/all")
    List<DeliveryDto> getAvailableDeliveryMethods();
}
