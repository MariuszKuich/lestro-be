package pl.mariuszk.orderservice.model.frontend.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderPanelDto {

    private long orderNo;
    private LocalDateTime cratedDate;
    private BigDecimal value;
    private String statusLabel;
    private Map<String, String> availableStatuses;
    private boolean isPaid;
    private String paymentLabel;
    private String deliveryLabel;
    private String clientName;
    private String mail;
    private String address;
}
