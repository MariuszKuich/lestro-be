package pl.mariuszk.employeepanelservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.mariuszk.employeepanelservice.feign.client.OrderClient;
import pl.mariuszk.employeepanelservice.feign.client.ProductClient;
import pl.mariuszk.orderservice.model.frontend.order.employeepanel.OrderStatusUpdateDto;
import pl.mariuszk.employeepanelservice.model.frontend.TokenDto;
import pl.mariuszk.employeepanelservice.service.security.TokenService;
import pl.mariuszk.orderservice.model.frontend.order.employeepanel.OrderPanelDto;
import pl.mariuszk.productservice.model.frontend.ProductDetailsDto;
import pl.mariuszk.productservice.model.frontend.UpdateProductDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lestro-panel")
@CrossOrigin(origins = "http://localhost:4202")
@RequiredArgsConstructor
public class EmployeePanelController {

    private final TokenService tokenService;
    private final OrderClient orderClient;
    private final ProductClient productClient;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> authenticateAndReturnJwtToken(Authentication authentication) {
        return ResponseEntity.ok(tokenService.generateToken(authentication));
    }

    @GetMapping("/secure/orders")
    public ResponseEntity<List<OrderPanelDto>> getOrders() {
       return ResponseEntity.ok(orderClient.getOrdersForEmployeePanel());
    }

    @PostMapping("/secure/mark-as-paid")
    public ResponseEntity<Void> markOrderAsPaid(@RequestBody long orderNumber) {
        orderClient.markOrderAsPaid(orderNumber);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/secure/update-order-status")
    public ResponseEntity<Void> updateOrderStatus(@RequestBody OrderStatusUpdateDto orderStatusUpdateDto) {
        orderClient.updateOrderStatus(orderStatusUpdateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/secure/products")
    public ResponseEntity<List<ProductDetailsDto>> getProducts() {
        return ResponseEntity.ok(productClient.getProductsForEmployeePanel());
    }

    @PostMapping("/secure/update-product")
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid UpdateProductDto updateProductDto) {
        productClient.updateProduct(updateProductDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/secure/delete-product/{productCode}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productCode) {
        productClient.deleteProduct(productCode);
        return ResponseEntity.ok().build();
    }
}
