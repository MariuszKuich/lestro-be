package pl.mariuszk.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import pl.mariuszk.elasticsearch.enums.PaymentCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "payment-index")
public class PaymentElastic {

    @Id
    private String id;

    @Field(name = "code", type = FieldType.Keyword)
    private PaymentCode code;

    @Field(name = "icon-class", type = FieldType.Keyword)
    private String iconClass;

    @Field(name = "name", type = FieldType.Keyword)
    private String name;
}
