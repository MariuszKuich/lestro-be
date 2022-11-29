package pl.mariuszk.orderservice.config.orika;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import pl.mariuszk.common.enums.OrderStatus;
import pl.mariuszk.elasticsearch.model.OrderElastic;
import pl.mariuszk.orderservice.model.frontend.order.OrderDto;

import java.time.LocalDateTime;

public class OrderDtoConverter extends CustomConverter<OrderDto, OrderElastic> {

    @Override
    public OrderElastic convert(OrderDto orderDto, Type<? extends OrderElastic> type, MappingContext mappingContext) {
        return OrderElastic.builder()
                .createdTimestamp(LocalDateTime.now())
                .status(OrderStatus.NEW)
                .paid(false)
                .deliveryMethod(orderDto.getDeliveryMethod())
                .paymentMethod(orderDto.getPaymentMethod())
                .name(orderDto.getClientData().getName())
                .surname(orderDto.getClientData().getSurname())
                .mail(orderDto.getClientData().getMail())
                .street(orderDto.getClientData().getStreet())
                .houseNo(orderDto.getClientData().getHouseNo())
                .apartmentNo(orderDto.getClientData().getApartNo())
                .zipCode(orderDto.getClientData().getZipCode())
                .city(orderDto.getClientData().getCity())
                .build();
    }
}
