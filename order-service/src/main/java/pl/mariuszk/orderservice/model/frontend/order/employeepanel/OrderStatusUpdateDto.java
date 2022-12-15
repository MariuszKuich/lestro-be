package pl.mariuszk.orderservice.model.frontend.order.employeepanel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusUpdateDto {

    private long orderNumber;
    private String newStatusCode;
}
