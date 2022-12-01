package pl.mariuszk.orderservice.service;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.mariuszk.elasticsearch.model.OrderElastic;
import pl.mariuszk.elasticsearch.model.ProductOrderElastic;
import pl.mariuszk.orderservice.elasticsearch.repository.OrderRepository;
import pl.mariuszk.orderservice.model.frontend.order.NewOrderDto;
import pl.mariuszk.orderservice.model.frontend.order.NewOrderItemDto;
import pl.mariuszk.orderservice.model.frontend.order.history.OrderDto;

import java.util.List;
import java.util.stream.Collectors;

import static pl.mariuszk.elasticsearch.enums.ElasticsearchField.CREATED_TIMESTAMP;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final long ORDER_NUMBERING_START = 1L;

    private final MapperFacade mapperFacade;
    private final OrderRepository orderRepository;
    private final OrderItemsService orderItemsService;

    public synchronized long createNewOrder(NewOrderDto orderDto) {
        long newOrderNumber = generateNewOrderNumber();

        orderRepository.save(getNewOrderObject(orderDto, newOrderNumber));
        orderItemsService.saveOrderItems(getProductsListForOrder(orderDto.getOrderItems(), newOrderNumber));

        return newOrderNumber;
    }

    private long generateNewOrderNumber() {
        return orderRepository.findTopByOrderByOrderNumberDesc()
                .map(latestOrderData -> latestOrderData.getOrderNumber() + 1)
                .orElse(ORDER_NUMBERING_START);
    }

    private OrderElastic getNewOrderObject(NewOrderDto orderDto, long newOrderNumber) {
        OrderElastic newOrderObject = mapperFacade.map(orderDto, OrderElastic.class);
        newOrderObject.setOrderNumber(newOrderNumber);
        return newOrderObject;
    }

    private List<ProductOrderElastic> getProductsListForOrder(List<NewOrderItemDto> orderItems, long newOrderNumber) {
        return orderItems.stream()
                .map(orderItem -> {
                    ProductOrderElastic productOrderElastic = mapperFacade.map(orderItem, ProductOrderElastic.class);
                    productOrderElastic.setOrderNumber(newOrderNumber);
                    return productOrderElastic;
                })
                .toList();
    }

    public List<OrderDto> getOrderHistory(String customerMail) {
        return orderRepository.findByMail(customerMail, Sort.by(CREATED_TIMESTAMP.getName()).descending()).stream()
                .map(order -> mapperFacade.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }
}
