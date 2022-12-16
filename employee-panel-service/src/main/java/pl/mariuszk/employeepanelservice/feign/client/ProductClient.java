package pl.mariuszk.employeepanelservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import pl.mariuszk.productservice.model.frontend.ProductDetailsDto;
import pl.mariuszk.productservice.model.frontend.UpdateProductDto;

import java.util.List;

@FeignClient("product-service")
public interface ProductClient {

    @GetMapping("/product/employee-panel-list")
    List<ProductDetailsDto> getProductsForEmployeePanel();

    @PostMapping("/product/update-product")
    void updateProduct(@RequestBody UpdateProductDto updateProductDto);

    @DeleteMapping("/product/delete-product/{productCode}")
    void deleteProduct(@PathVariable String productCode);
}
