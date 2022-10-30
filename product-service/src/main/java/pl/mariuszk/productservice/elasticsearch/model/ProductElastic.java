package pl.mariuszk.productservice.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "product-index")
public class ProductElastic {

    @Id
    private String id;

    @Field(name = "code", type = FieldType.Keyword)
    private String code;

    @Field(name = "name", type = FieldType.Keyword)
    private String name;

    @Field(name = "description", type = FieldType.Text, index = false)
    private String description;

    @Field(name = "img", type = FieldType.Text, index = false)
    private List<String> imgLinks;

    @Field(name = "price", type = FieldType.Double)
    private double price;

    @Field(name = "height", type = FieldType.Long)
    private int height;

    @Field(name = "width", type = FieldType.Long)
    private int width;

    @Field(name = "length", type = FieldType.Long)
    private int length;

    @Field(name = "plants", type = FieldType.Keyword)
    private List<String> plants;
}
