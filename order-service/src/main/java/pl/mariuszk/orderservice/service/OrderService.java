package pl.mariuszk.orderservice.service;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import pl.mariuszk.elasticsearch.model.OrderElastic;
import pl.mariuszk.elasticsearch.model.ProductOrderElastic;
import pl.mariuszk.orderservice.elasticsearch.repository.OrderRepository;
import pl.mariuszk.orderservice.elasticsearch.repository.ProductOrderRepository;
import pl.mariuszk.orderservice.model.frontend.order.OrderDto;
import pl.mariuszk.orderservice.model.frontend.order.OrderItemDto;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final long ORDER_NUMBERING_START = 1L;

    private final MapperFacade mapperFacade;
    private final OrderRepository orderRepository;
    private final ProductOrderRepository productOrderRepository;

    public synchronized long createNewOrder(OrderDto orderDto) {
        long newOrderNumber = generateNewOrderNumber();

        orderRepository.save(getNewOrderObject(orderDto, newOrderNumber));
        productOrderRepository.saveAll(getOrderProductsList(orderDto.getOrderItems(), newOrderNumber));

        return newOrderNumber;
    }

    private long generateNewOrderNumber() {
        return orderRepository.findTopByOrderByOrderNumberDesc()
                .map(latestOrderData -> latestOrderData.getOrderNumber() + 1)
                .orElse(ORDER_NUMBERING_START);
    }

    private OrderElastic getNewOrderObject(OrderDto orderDto, long newOrderNumber) {
        OrderElastic newOrderObject = mapperFacade.map(orderDto, OrderElastic.class);
        newOrderObject.setOrderNumber(newOrderNumber);
        return newOrderObject;
    }

    private List<ProductOrderElastic> getOrderProductsList(List<OrderItemDto> orderItems, long newOrderNumber) {
        return orderItems.stream()
                .map(orderItem -> {
                    ProductOrderElastic productOrderElastic = mapperFacade.map(orderItem, ProductOrderElastic.class);
                    productOrderElastic.setOrderNumber(newOrderNumber);
                    return productOrderElastic;
                })
                .toList();
    }

    public BigDecimal getTotalOrderValue(long orderNumber) {
        return productOrderRepository.findByOrderNumber(orderNumber)
                .stream()
                .map(productOrder -> BigDecimal.valueOf(productOrder.getQuantity()).multiply(BigDecimal.valueOf(productOrder.getPrice())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
