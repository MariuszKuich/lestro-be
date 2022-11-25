package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.mariuszk.orderservice.model.frontend.order.OrderDto;

import java.math.BigDecimal;

@FeignClient("order-service")
public interface OrderClient {

    @PostMapping("/order/new")
    long createNewOrder(@RequestBody OrderDto orderDto);

    @GetMapping("/order/total-value/{orderNumber}")
    BigDecimal getTotalOrderValue(@PathVariable long orderNumber);
}
