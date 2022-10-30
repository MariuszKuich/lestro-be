package pl.mariuszk.productservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest extends BasePagingRequest {

    private String code;
    private String name;
    private Double priceMin;
    private Double priceMax;
    private List<String> plants;
    private Integer heightMax;
    private Integer widthMax;
    private Integer lengthMax;
}
