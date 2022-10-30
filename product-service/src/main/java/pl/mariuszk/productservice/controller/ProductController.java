package pl.mariuszk.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mariuszk.productservice.elasticsearch.model.ProductElastic;
import pl.mariuszk.productservice.request.ProductRequest;
import pl.mariuszk.productservice.service.ProductService;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<Page<ProductElastic>> getProductsList(ProductRequest request) {
        return ResponseEntity.ok(productService.getProductsList(request));
    }
}
