package pl.mariuszk.frontendcommunicationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mariuszk.productservice.model.frontend.ProductDetailsDto;
import pl.mariuszk.productservice.model.frontend.ProductDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@FeignClient("product-service")
public interface ProductClient {

    @GetMapping("/product/list")
    Page<ProductDto> getProductsList(@RequestParam Map<String, String> request);

    @GetMapping("/product/available-plants")
    List<String> getAvailablePlants();

    @GetMapping("/product/{code}")
    Optional<ProductDetailsDto> getProductDetails(@PathVariable("code") String code);
}
