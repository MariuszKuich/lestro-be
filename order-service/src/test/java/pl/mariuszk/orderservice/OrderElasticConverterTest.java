package pl.mariuszk.orderservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Sort;
import pl.mariuszk.common.enums.DeliveryCode;
import pl.mariuszk.common.enums.OrderStatus;
import pl.mariuszk.common.enums.PaymentCode;
import pl.mariuszk.elasticsearch.model.OrderElastic;
import pl.mariuszk.elasticsearch.model.ProductOrderElastic;
import pl.mariuszk.orderservice.config.orika.OrderElasticConverter;
import pl.mariuszk.orderservice.model.frontend.order.history.OrderDto;
import pl.mariuszk.orderservice.model.frontend.order.history.OrderItemDto;
import pl.mariuszk.orderservice.service.ConverterService;
import pl.mariuszk.orderservice.service.OrderItemsService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;

class OrderElasticConverterTest {

    private final OrderItemsService orderItemsService = Mockito.mock(OrderItemsService.class);
    private final ConverterService converterService = new ConverterService();

    @Test
    void convertOrderElasticToOrderDto() {
        //arrange
        OrderElastic inputData = OrderElastic.builder()
                .orderNumber(1)
                .createdTimestamp(LocalDateTime.now())
                .paid(false)
                .status(OrderStatus.NEW)
                .deliveryMethod(DeliveryCode.PDP)
                .paymentMethod(PaymentCode.PAY_Y)
                .street("Test")
                .houseNo("1")
                .apartmentNo("1")
                .zipCode("11-111")
                .city("Bydgoszcz")
                .build();

        Mockito.doReturn(Collections.singletonList(
                ProductOrderElastic.builder()
                        .code("STR-1")
                        .name("Stroik Słonecznikowy [OZDOBY]")
                        .quantity(2)
                        .price(59.99)
                        .img("https://i.ibb.co/r3QF0wR/1.jpg")
                        .build()))
                .when(orderItemsService).getOrderItems(any(Long.class), any(Sort.class));
        Mockito.when(orderItemsService.getTotalOrderItemsValueIncludingDeliveryCost(any(), any()))
                .thenReturn(BigDecimal.valueOf(137.97));

        OrderElasticConverter converter = new OrderElasticConverter(orderItemsService, converterService);

        //act
        OrderDto result = converter.convert(inputData, null, null);

        //assert
        OrderDto expectedResult = OrderDto.builder()
                .orderNumber(1)
                .createdDate(inputData.getCreatedTimestamp().toLocalDate())
                .value(BigDecimal.valueOf(137.97))
                .isPaid(false)
                .statusLabel(OrderStatus.NEW.getName())
                .deliveryLabel(DeliveryCode.PDP.getName())
                .paymentLabel(PaymentCode.PAY_Y.getName())
                .address("Test 1/1, 11-111 Bydgoszcz")
                .items(Collections.singletonList(
                        OrderItemDto.builder()
                                .productCode("STR-1")
                                .name("Stroik Słonecznikowy [OZDOBY]")
                                .quantity(2)
                                .price(BigDecimal.valueOf(59.99))
                                .value(BigDecimal.valueOf(119.98))
                                .img("https://i.ibb.co/r3QF0wR/1.jpg")
                                .build()
                ))
                .build();
        Assertions.assertEquals(expectedResult, result);
    }
}
