package pl.mariuszk.paymentservice.model.frontend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mariuszk.common.enums.PaymentCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private PaymentCode code;
    private String iconClass;
    private String name;
}
