package pl.mariuszk.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import pl.mariuszk.elasticsearch.enums.DeliveryCode;
import pl.mariuszk.elasticsearch.enums.OrderStatus;
import pl.mariuszk.elasticsearch.enums.PaymentCode;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "order-index")
public class OrderElastic {

    @Id
    private String id;

    @Field(name = "order-number", type = FieldType.Long)
    private long orderNumber;

    @Field(name = "created-timestamp", type = FieldType.Date)
    private LocalDateTime createdTimestamp;

    @Field(name = "status", type = FieldType.Keyword)
    private OrderStatus status;

    @Field(name = "paid", type = FieldType.Boolean)
    private boolean paid;

    @Field(name = "delivery-method", type = FieldType.Keyword)
    private DeliveryCode deliveryMethod;

    @Field(name = "payment-method", type = FieldType.Keyword)
    private PaymentCode paymentMethod;

    @Field(name = "name", type = FieldType.Keyword)
    private String name;

    @Field(name = "surname", type = FieldType.Keyword)
    private String surname;

    @Field(name = "mail", type = FieldType.Keyword)
    private String mail;

    @Field(name = "street", type = FieldType.Keyword)
    private String street;

    @Field(name = "house-no", type = FieldType.Keyword)
    private String houseNo;

    @Field(name = "apartment-no", type = FieldType.Keyword)
    private String apartmentNo;

    @Field(name = "zip-code", type = FieldType.Keyword)
    private String zipCode;

    @Field(name = "city", type = FieldType.Keyword)
    private String city;
}
