package pl.mariuszk.employeepanelservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.mariuszk.orderservice.model.frontend.order.employeepanel.OrderStatusUpdateDto;
import pl.mariuszk.orderservice.model.frontend.order.employeepanel.OrderPanelDto;

import java.util.List;

@FeignClient("order-service")
public interface OrderClient {

    @GetMapping("/order/employee-panel-list")
    List<OrderPanelDto> getOrdersForEmployeePanel();

    @PostMapping("/order/mark-as-paid")
    void markOrderAsPaid(@RequestBody long orderNumber);

    @PostMapping("/order/update-order-status")
    void updateOrderStatus(@RequestBody OrderStatusUpdateDto orderStatusUpdateDto);
}
