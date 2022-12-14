package pl.mariuszk.orderservice.config.orika;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import pl.mariuszk.elasticsearch.model.OrderElastic;
import pl.mariuszk.orderservice.model.frontend.order.OrderPanelDto;
import pl.mariuszk.orderservice.service.ConverterService;
import pl.mariuszk.orderservice.service.OrderItemsService;

@RequiredArgsConstructor
public class OrderPanelConverter extends CustomConverter<OrderElastic, OrderPanelDto> {

    private final OrderItemsService orderItemsService;
    private final ConverterService converterService;

    @Override
    public OrderPanelDto convert(OrderElastic orderElastic, Type<? extends OrderPanelDto> type, MappingContext mappingContext) {
        return OrderPanelDto.builder()
                .orderNo(orderElastic.getOrderNumber())
                .cratedDate(orderElastic.getCreatedTimestamp())
                .value(orderItemsService.getTotalOrderItemsValue(orderElastic.getOrderNumber()))
                .statusLabel(orderElastic.getStatus().getName())
                .availableStatuses(converterService.getAvailableStatuses(orderElastic))
                .isPaid(orderElastic.isPaid())
                .paymentLabel(orderElastic.getPaymentMethod().getName())
                .deliveryLabel(orderElastic.getDeliveryMethod().getName())
                .clientName(converterService.buildClientFullName(orderElastic))
                .mail(orderElastic.getMail())
                .address(converterService.buildAddress(orderElastic))
                .build();
    }
}
