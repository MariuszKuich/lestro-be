package pl.mariuszk.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import pl.mariuszk.common.enums.CompositionElemType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "configurator-index")
public class ConfiguratorElementElastic {

    @Id
    private String id;

    @Field(name = "type", type = FieldType.Keyword)
    private CompositionElemType type;

    @Field(name = "code", type = FieldType.Keyword)
    private String code;

    @Field(name = "name", type = FieldType.Keyword)
    private String name;

    @Field(name = "price", type = FieldType.Double)
    private double price;
}
