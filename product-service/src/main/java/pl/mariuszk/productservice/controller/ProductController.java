package pl.mariuszk.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pl.mariuszk.common.enums.CompositionElemType;
import pl.mariuszk.productservice.model.frontend.*;
import pl.mariuszk.productservice.request.ProductRequest;
import pl.mariuszk.productservice.service.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
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

    @GetMapping("/configurator/options")
    public Map<CompositionElemType, List<CompositionElementDto>> getAvailableConfiguratorOptions() {
        return productService.getAvailableConfiguratorOptions();
    }

    @GetMapping("/employee-panel-list")
    public List<ProductDetailsDto> getProductsForEmployeePanel() {
        return productService.getProductsForEmployeePanel();
    }

    @PostMapping("/update-product")
    public void updateProduct(@RequestBody @Valid UpdateProductDto updateProductDto) {
        productService.updateProduct(updateProductDto);
    }

    @DeleteMapping("/delete-product/{productCode}")
    public void deleteProduct(@PathVariable String productCode) {
        productService.deleteProduct(productCode);
    }

    @PostMapping("/new-product")
    public void addNewProduct(@RequestBody @Valid NewProductDto newProductDto) {
        productService.addNewProduct(newProductDto);
    }
}
