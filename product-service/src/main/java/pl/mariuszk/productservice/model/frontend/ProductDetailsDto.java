package pl.mariuszk.productservice.model.frontend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDto {

    private String code;
    private String name;
    private String description;
    private List<String> imgLinks;
    private double price;
    private int height;
    private int width;
    private int length;
    private List<String> plants;
}
