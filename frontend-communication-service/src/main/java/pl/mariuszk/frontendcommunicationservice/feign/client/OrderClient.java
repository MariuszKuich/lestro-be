package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import pl.mariuszk.orderservice.model.frontend.order.NewOrderDto;
import pl.mariuszk.orderservice.model.frontend.order.history.OrderDto;

import java.math.BigDecimal;
import java.util.List;

@FeignClient("order-service")
public interface OrderClient {

    @PostMapping("/order/new")
    long createNewOrder(@RequestBody NewOrderDto orderDto);

    @GetMapping("/order/total-value/{orderNumber}")
    BigDecimal getTotalOrderValue(@PathVariable long orderNumber);

    @GetMapping("/order/order-history")
    List<OrderDto> getOrderHistory(@RequestParam String customerMail);
}
