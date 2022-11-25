package pl.mariuszk.paymentservice.model.frontend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewPaymentDataDto {

    private BigDecimal orderValue;
    private long orderNumber;
}
