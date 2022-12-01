package pl.mariuszk.orderservice.model.frontend.order.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private long orderNumber;
    private LocalDateTime createdAt;
    private BigDecimal value;
    private boolean isPaid;
    private String statusLabel;
    private String deliveryLabel;
    private String paymentLabel;
    private String address;
    private List<OrderItemDto> items;
}
