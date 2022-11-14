package pl.mariuszk.orderservice.model.frontend.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mariuszk.elasticsearch.enums.DeliveryCode;
import pl.mariuszk.elasticsearch.enums.PaymentCode;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    @NotNull
    @Valid
    private ClientDataDto clientData;

    @NotNull
    private DeliveryCode deliveryMethod;

    @NotNull
    private PaymentCode paymentMethod;

    @NotEmpty
    @Valid
    private List<OrderItemDto> orderItems;
}
