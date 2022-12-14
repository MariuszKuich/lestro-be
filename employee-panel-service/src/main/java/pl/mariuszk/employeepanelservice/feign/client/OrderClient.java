package pl.mariuszk.employeepanelservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mariuszk.orderservice.model.frontend.order.employeepanel.OrderPanelDto;

import java.util.List;

@FeignClient("order-service")
public interface OrderClient {

    @GetMapping("/order/employee-panel-list")
    List<OrderPanelDto> getOrdersForEmployeePanel();
}
