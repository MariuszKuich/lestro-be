package pl.mariuszk.orderservice.model.frontend.order.employeepanel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderPanelItemDto {

    private String productCode;
    private String name;
    private long quantity;
    private String plantName;
    private String decorationName;
    private String ornamentName;
    private String potName;
}
