package pl.mariuszk.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pl.mariuszk.productservice.model.frontend.ProductDetailsDto;
import pl.mariuszk.productservice.model.frontend.ProductDto;
import pl.mariuszk.productservice.request.ProductRequest;
import pl.mariuszk.productservice.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public Page<ProductDto> getProductsList(ProductRequest request) {
        return productService.getProductsList(request);
    }

    @GetMapping("/available-plants")
    public List<String> getAvailablePlants() {
        return productService.getGroupedPlantsFromEveryProduct();
    }

    @GetMapping("/{code}")
    public Optional<ProductDetailsDto> getProductDetails(@PathVariable("code") String code) {
        return productService.getProductDetails(code);
    }
}
