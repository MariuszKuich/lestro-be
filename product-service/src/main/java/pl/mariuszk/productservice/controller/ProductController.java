package pl.mariuszk.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mariuszk.productservice.model.frontend.ProductDetailsDto;
import pl.mariuszk.productservice.model.frontend.ProductDto;
import pl.mariuszk.productservice.request.ProductRequest;
import pl.mariuszk.productservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<Page<ProductDto>> getProductsList(ProductRequest request) {
        return ResponseEntity.ok(productService.getProductsList(request));
    }

    @GetMapping("/available-plants")
    public ResponseEntity<List<String>> getAvailablePlants() {
        return ResponseEntity.ok(productService.getGroupedPlantsFromEveryProduct());
    }

    @GetMapping("/{code}")
    public ResponseEntity<ProductDetailsDto> getProductDetails(@PathVariable("code") String code) {
        return productService.getProductDetails(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
