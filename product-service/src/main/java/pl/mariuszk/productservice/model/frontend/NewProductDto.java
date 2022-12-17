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
public class NewProductDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Min(1)
    private int width;

    @Min(1)
    private int height;

    @Min(1)
    private int length;

    @NotNull
    @Min(1)
    private BigDecimal price;

    @NotEmpty
    private List<String> plants;

    @NotEmpty
    private List<String> imgLinks;
}
