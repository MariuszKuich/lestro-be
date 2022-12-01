package pl.mariuszk.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.mariuszk.elasticsearch.model.ProductOrderElastic;
import pl.mariuszk.orderservice.elasticsearch.repository.ProductOrderRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemsService {

    private final ProductOrderRepository productOrderRepository;

    public BigDecimal getTotalOrderItemsValue(long orderNumber) {
        return getTotalOrderItemsValue(productOrderRepository.findByOrderNumber(orderNumber));
    }

    public BigDecimal getTotalOrderItemsValue(List<ProductOrderElastic> orderItems) {
        return orderItems.stream()
                .map(productOrder -> BigDecimal.valueOf(productOrder.getQuantity()).multiply(BigDecimal.valueOf(productOrder.getPrice())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<ProductOrderElastic> getOrderItems(long orderNumber, Sort sort) {
        return productOrderRepository.findByOrderNumber(orderNumber, sort);
    }

    public void saveOrderItems(List<ProductOrderElastic> orderItems) {
        productOrderRepository.saveAll(orderItems);
    }
}
