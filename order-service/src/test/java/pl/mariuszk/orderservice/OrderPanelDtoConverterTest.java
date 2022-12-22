package pl.mariuszk.orderservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Sort;
import pl.mariuszk.common.enums.DeliveryCode;
import pl.mariuszk.common.enums.OrderStatus;
import pl.mariuszk.common.enums.PaymentCode;
import pl.mariuszk.elasticsearch.model.OrderElastic;
import pl.mariuszk.orderservice.config.orika.OrderPanelDtoConverter;
import pl.mariuszk.orderservice.model.frontend.order.employeepanel.OrderPanelDto;
import pl.mariuszk.orderservice.service.ConverterService;
import pl.mariuszk.orderservice.service.OrderItemsService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;

class OrderPanelDtoConverterTest {

    private final OrderItemsService orderItemsService = Mockito.mock(OrderItemsService.class);
    private final ConverterService converterService = new ConverterService();

    @Test
    void convertOrderElasticToOrderPanelDto_orderNotPaid() {
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
                .zipCode("11-111")
                .city("Bydgoszcz")
                .name("Mariusz")
                .surname("Kuich")
                .mail("markui000@pbs.edu.pl")
                .build();

        Mockito.doReturn(Collections.emptyList()).when(orderItemsService).getOrderItems(any(Long.class), any(Sort.class));
        Mockito.when(orderItemsService.getTotalOrderItemsValueIncludingDeliveryCost(any(), any()))
                .thenReturn(BigDecimal.valueOf(137.97));

        OrderPanelDtoConverter converter = new OrderPanelDtoConverter(orderItemsService, converterService);

        //act
        OrderPanelDto result = converter.convert(inputData, null, null);

        //assert
        OrderPanelDto expectedResult = OrderPanelDto.builder()
                .orderNo(1)
                .createdDate(inputData.getCreatedTimestamp().toLocalDate())
                .value(BigDecimal.valueOf(137.97))
                .statusLabel(OrderStatus.NEW.getName())
                .availableStatuses(Collections.emptyMap())
                .isPaid(false)
                .paymentLabel(PaymentCode.PAY_Y.getName())
                .deliveryLabel(DeliveryCode.PDP.getName())
                .clientName("Mariusz Kuich")
                .mail("markui000@pbs.edu.pl")
                .address("Test 1, 11-111 Bydgoszcz")
                .items(Collections.emptyList())
                .build();

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void convertOrderElasticToOrderPanelDto_orderPaid() {
        //arrange
        OrderElastic inputData = OrderElastic.builder()
                .orderNumber(1)
                .createdTimestamp(LocalDateTime.now())
                .paid(true)
                .status(OrderStatus.PAID)
                .deliveryMethod(DeliveryCode.PDP)
                .paymentMethod(PaymentCode.PAY_Y)
                .street("Test")
                .houseNo("1")
                .zipCode("11-111")
                .city("Bydgoszcz")
                .name("Mariusz")
                .surname("Kuich")
                .mail("markui000@pbs.edu.pl")
                .build();

        Mockito.doReturn(Collections.emptyList()).when(orderItemsService).getOrderItems(any(Long.class), any(Sort.class));
        Mockito.when(orderItemsService.getTotalOrderItemsValueIncludingDeliveryCost(any(), any()))
                .thenReturn(BigDecimal.valueOf(137.97));

        OrderPanelDtoConverter converter = new OrderPanelDtoConverter(orderItemsService, converterService);

        //act
        OrderPanelDto result = converter.convert(inputData, null, null);

        //assert
        Map<String, String> expectedAvailableStatusesMap = new HashMap<>();
        expectedAvailableStatusesMap.put("IN_PROGRESS", "W trakcie realizacji");
        expectedAvailableStatusesMap.put("SENT", "Wysłane");
        expectedAvailableStatusesMap.put("RECEIVED", "Odebrane");

        OrderPanelDto expectedResult = OrderPanelDto.builder()
                .orderNo(1)
                .createdDate(inputData.getCreatedTimestamp().toLocalDate())
                .value(BigDecimal.valueOf(137.97))
                .statusLabel(OrderStatus.PAID.getName())
                .availableStatuses(expectedAvailableStatusesMap)
                .isPaid(true)
                .paymentLabel(PaymentCode.PAY_Y.getName())
                .deliveryLabel(DeliveryCode.PDP.getName())
                .clientName("Mariusz Kuich")
                .mail("markui000@pbs.edu.pl")
                .address("Test 1, 11-111 Bydgoszcz")
                .items(Collections.emptyList())
                .build();

        Assertions.assertEquals(expectedResult, result);
    }
}
