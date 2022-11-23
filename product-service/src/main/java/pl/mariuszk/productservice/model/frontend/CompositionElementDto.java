package pl.mariuszk.productservice.model.frontend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mariuszk.common.enums.CompositionElemType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompositionElementDto {

    private CompositionElemType type;
    private String code;
    private String name;
    private double price;
}
