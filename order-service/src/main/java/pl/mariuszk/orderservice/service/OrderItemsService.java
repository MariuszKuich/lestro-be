package pl.mariuszk.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.mariuszk.common.enums.DeliveryCode;
import pl.mariuszk.common.exceptions.DeliveryMethodNotFoundException;
import pl.mariuszk.common.exceptions.OrderNotFoundException;
import pl.mariuszk.elasticsearch.model.DeliveryElastic;
import pl.mariuszk.elasticsearch.model.OrderElastic;
import pl.mariuszk.elasticsearch.model.ProductOrderElastic;
import pl.mariuszk.orderservice.elasticsearch.repository.DeliveryRepository;
import pl.mariuszk.orderservice.elasticsearch.repository.OrderRepository;
import pl.mariuszk.orderservice.elasticsearch.repository.ProductOrderRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemsService {

    private final ProductOrderRepository productOrderRepository;
    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;

    public BigDecimal getTotalOrderItemsValueIncludingDeliveryCost(long orderNumber) {
        OrderElastic orderElastic = orderRepository.findByOrderNumber(orderNumber).orElseThrow(OrderNotFoundException::new);
        return getTotalOrderItemsValueIncludingDeliveryCost(productOrderRepository.findByOrderNumber(orderNumber),
                orderElastic.getDeliveryMethod());

    }

    public BigDecimal getTotalOrderItemsValueIncludingDeliveryCost(List<ProductOrderElastic> orderItems, DeliveryCode deliveryCode) {
        BigDecimal itemsValue = orderItems.stream()
                .map(productOrder -> BigDecimal.valueOf(productOrder.getQuantity()).multiply(BigDecimal.valueOf(productOrder.getPrice())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return includeDeliveryCost(itemsValue, deliveryCode);
    }

    public BigDecimal includeDeliveryCost(BigDecimal itemsValue, DeliveryCode deliveryCode) {
        DeliveryElastic deliveryElastic = deliveryRepository.findByCode(deliveryCode)
                .orElseThrow(DeliveryMethodNotFoundException::new);

        return itemsValue.add(BigDecimal.valueOf(deliveryElastic.getPrice())).setScale(2, RoundingMode.HALF_UP);
    }

    public List<ProductOrderElastic> getOrderItems(long orderNumber, Sort sort) {
        return productOrderRepository.findByOrderNumber(orderNumber, sort);
    }

    public void saveOrderItems(List<ProductOrderElastic> orderItems) {
        productOrderRepository.saveAll(orderItems);
    }
}
