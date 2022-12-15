package pl.mariuszk.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mariuszk.orderservice.model.frontend.order.NewOrderDto;
import pl.mariuszk.orderservice.model.frontend.order.employeepanel.OrderPanelDto;
import pl.mariuszk.orderservice.model.frontend.order.employeepanel.OrderStatusUpdateDto;
import pl.mariuszk.orderservice.model.frontend.order.history.OrderDto;
import pl.mariuszk.orderservice.service.OrderItemsService;
import pl.mariuszk.orderservice.service.OrderService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderItemsService orderItemsService;

    @PostMapping("/new")
    public long createNewOrder(@Valid @RequestBody NewOrderDto orderDto) {
        return orderService.createNewOrder(orderDto);
    }

    @GetMapping("/total-value/{orderNumber}")
    public BigDecimal getTotalOrderValue(@PathVariable long orderNumber) {
        return orderItemsService.getTotalOrderItemsValue(orderNumber);
    }

    @GetMapping("/order-history")
    public List<OrderDto> getOrderHistory(@RequestParam String customerMail) {
        return orderService.getOrderHistory(customerMail);
    }

    @GetMapping("/employee-panel-list")
    public List<OrderPanelDto> getOrdersForEmployeePanel() {
        return orderService.getOrdersForEmployeePanel();
    }

    @PostMapping("/mark-as-paid")
    void markOrderAsPaid(@RequestBody long orderNumber) {
        orderService.markOrderAsPaid(orderNumber);
    }

    @PostMapping("/update-order-status")
    void updateOrderStatus(@RequestBody OrderStatusUpdateDto orderStatusUpdateDto) {
        orderService.updateOrderStatus(orderStatusUpdateDto);
    }
}
