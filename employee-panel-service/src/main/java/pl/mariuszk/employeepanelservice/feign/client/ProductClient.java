package pl.mariuszk.employeepanelservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mariuszk.productservice.model.frontend.ProductDetailsDto;

import java.util.List;

@FeignClient("product-service")
public interface ProductClient {

    @GetMapping("/product/employee-panel-list")
    List<ProductDetailsDto> getProductsForEmployeePanel();
}
