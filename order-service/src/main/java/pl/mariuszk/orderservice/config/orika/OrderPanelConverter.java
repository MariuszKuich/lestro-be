package pl.mariuszk.orderservice.config.orika;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.data.domain.Sort;
import pl.mariuszk.elasticsearch.model.OrderElastic;
import pl.mariuszk.elasticsearch.model.ProductOrderElastic;
import pl.mariuszk.orderservice.model.frontend.order.employeepanel.OrderPanelDto;
import pl.mariuszk.orderservice.model.frontend.order.employeepanel.OrderPanelItemDto;
import pl.mariuszk.orderservice.service.ConverterService;
import pl.mariuszk.orderservice.service.OrderItemsService;

import java.util.List;
import java.util.stream.Collectors;

import static pl.mariuszk.elasticsearch.enums.ElasticsearchField.NAME;

@RequiredArgsConstructor
public class OrderPanelConverter extends CustomConverter<OrderElastic, OrderPanelDto> {

    private final OrderItemsService orderItemsService;
    private final ConverterService converterService;

    @Override
    public OrderPanelDto convert(OrderElastic orderElastic, Type<? extends OrderPanelDto> type, MappingContext mappingContext) {
        List<ProductOrderElastic> orderItems =
                orderItemsService.getOrderItems(orderElastic.getOrderNumber(), Sort.by(NAME.getName()).ascending());

        return OrderPanelDto.builder()
                .orderNo(orderElastic.getOrderNumber())
                .createdDate(orderElastic.getCreatedTimestamp().toLocalDate())
                .value(orderItemsService.getTotalOrderItemsValue(orderItems))
                .statusLabel(orderElastic.getStatus().getName())
                .availableStatuses(converterService.getAvailableStatuses(orderElastic))
                .isPaid(orderElastic.isPaid())
                .paymentLabel(orderElastic.getPaymentMethod().getName())
                .deliveryLabel(orderElastic.getDeliveryMethod().getName())
                .clientName(converterService.buildClientFullName(orderElastic))
                .mail(orderElastic.getMail())
                .address(converterService.buildAddress(orderElastic))
                .items(buildOrderItemsDtos(orderItems))
                .build();
    }

    private List<OrderPanelItemDto> buildOrderItemsDtos(List<ProductOrderElastic> orderItems) {
        return orderItems.stream()
                .map(orderItem -> OrderPanelItemDto.builder()
                        .productCode(orderItem.getCode())
                        .name(orderItem.getName())
                        .quantity(orderItem.getQuantity())
                        .plantName(orderItem.getPlantName())
                        .decorationName(orderItem.getDecorationName())
                        .ornamentName(orderItem.getOrnamentName())
                        .potName(orderItem.getPotName())
                        .build())
                .collect(Collectors.toList());
    }
}
