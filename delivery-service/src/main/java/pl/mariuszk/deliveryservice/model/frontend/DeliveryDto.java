package pl.mariuszk.deliveryservice.model.frontend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mariuszk.elasticsearch.enums.DeliveryCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto {

    private DeliveryCode code;
    private String iconClass;
    private String name;
    private double price;
}
