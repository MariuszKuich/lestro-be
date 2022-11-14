package pl.mariuszk.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mariuszk.orderservice.model.frontend.order.OrderDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    @PostMapping("/new")
    public void createNewOrder(@Valid @RequestBody OrderDto orderDto) {
        System.out.println(orderDto);
    }
}
