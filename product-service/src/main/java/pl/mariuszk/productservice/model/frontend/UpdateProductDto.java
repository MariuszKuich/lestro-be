package pl.mariuszk.productservice.model.frontend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDto {

    @NotBlank
    private String code;
    @NotBlank
    private String description;
    @NotNull
    @Min(1)
    private BigDecimal price;
    @NotEmpty
    private List<String> imgLinks;
}
