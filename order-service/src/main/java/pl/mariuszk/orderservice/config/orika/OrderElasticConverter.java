package pl.mariuszk.orderservice.config.orika;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.data.domain.Sort;
import pl.mariuszk.elasticsearch.model.OrderElastic;
import pl.mariuszk.elasticsearch.model.ProductOrderElastic;
import pl.mariuszk.orderservice.model.frontend.order.history.OrderDto;
import pl.mariuszk.orderservice.model.frontend.order.history.OrderItemDto;
import pl.mariuszk.orderservice.service.ConverterService;
import pl.mariuszk.orderservice.service.OrderItemsService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static pl.mariuszk.elasticsearch.enums.ElasticsearchField.NAME;

@RequiredArgsConstructor
public class OrderElasticConverter extends CustomConverter<OrderElastic, OrderDto> {

    private final OrderItemsService orderItemsService;
    private final ConverterService converterService;

    @Override
    public OrderDto convert(OrderElastic orderElastic, Type<? extends OrderDto> type, MappingContext mappingContext) {
        List<ProductOrderElastic> orderItems =
                orderItemsService.getOrderItems(orderElastic.getOrderNumber(), Sort.by(NAME.getName()).ascending());

        return OrderDto.builder()
                .orderNumber(orderElastic.getOrderNumber())
                .createdDate(orderElastic.getCreatedTimestamp().toLocalDate())
                .value(orderItemsService.getTotalOrderItemsValue(orderItems))
                .isPaid(orderElastic.isPaid())
                .statusLabel(orderElastic.getStatus().getName())
                .deliveryLabel(orderElastic.getDeliveryMethod().getName())
                .paymentLabel(orderElastic.getPaymentMethod().getName())
                .address(converterService.buildAddress(orderElastic))
                .items(buildOrderItemsDtos(orderItems))
                .build();
    }

    private List<OrderItemDto> buildOrderItemsDtos(List<ProductOrderElastic> orderItems) {
        return orderItems.stream()
                .map(orderItem -> OrderItemDto.builder()
                        .productCode(orderItem.getCode())
                        .name(orderItem.getName())
                        .quantity(orderItem.getQuantity())
                        .price(BigDecimal.valueOf(orderItem.getPrice()))
                        .value(getOrderItemValue(orderItem.getPrice(), orderItem.getQuantity()))
                        .img(orderItem.getImg())
                        .plantName(orderItem.getPlantName())
                        .decorationName(orderItem.getDecorationName())
                        .ornamentName(orderItem.getOrnamentName())
                        .potName(orderItem.getPotName())
                        .build())
                .collect(Collectors.toList());
    }

    private BigDecimal getOrderItemValue(double price, long quantity) {
        return BigDecimal.valueOf(price).multiply(BigDecimal.valueOf(quantity));
    }
}
