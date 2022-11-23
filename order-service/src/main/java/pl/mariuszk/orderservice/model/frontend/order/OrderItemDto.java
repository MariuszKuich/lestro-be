package pl.mariuszk.orderservice.model.frontend.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    @NotBlank
    private String productCode;

    @Min(1)
    private int quantity;

    @NotBlank
    private String name;

    @NotBlank
    private String img;

    private String plantCode;

    private String decorationCode;

    private String ornamentCode;

    private String potCode;
}
