package pl.mariuszk.orderservice.model.frontend.order.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {

    private String productCode;
    private String name;
    private long quantity;
    private BigDecimal price;
    private BigDecimal value;
    private String img;
    private String plantName;
    private String decorationName;
    private String ornamentName;
    private String potName;
}
