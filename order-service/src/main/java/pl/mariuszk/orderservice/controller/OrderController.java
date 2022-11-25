package pl.mariuszk.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mariuszk.orderservice.model.frontend.order.OrderDto;
import pl.mariuszk.orderservice.service.OrderService;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/new")
    public long createNewOrder(@Valid @RequestBody OrderDto orderDto) {
        return orderService.createNewOrder(orderDto);
    }

    @GetMapping("/total-value/{orderNumber}")
    public BigDecimal getTotalOrderValue(@PathVariable long orderNumber) {
        return orderService.getTotalOrderValue(orderNumber);
    }
}
