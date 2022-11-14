package pl.mariuszk.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "product-order-index")
@Builder
public class ProductOrderElastic {

    @Id
    private String id;

    @Field(name = "order-number", type = FieldType.Long)
    private long orderNumber;

    @Field(name = "quantity", type = FieldType.Long)
    private long quantity;

    @Field(name = "code", type = FieldType.Keyword)
    private String code;

    @Field(name = "name", type = FieldType.Keyword)
    private String name;

    @Field(name = "img", type = FieldType.Text)
    private String img;

    @Field(name = "price", type = FieldType.Double)
    private double price;
}
