package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.mariuszk.orderservice.model.frontend.order.OrderDto;

@FeignClient("order-service")
public interface OrderClient {

    @PostMapping("/order/new")
    long createNewOrder(@RequestBody OrderDto orderDto);
}
