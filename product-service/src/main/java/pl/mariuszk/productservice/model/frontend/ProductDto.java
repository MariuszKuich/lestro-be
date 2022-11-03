package pl.mariuszk.productservice.model.frontend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String name;
    private String code;
    private double price;
    @JsonProperty("imgLinks")
    private List<String> imgs;
}
