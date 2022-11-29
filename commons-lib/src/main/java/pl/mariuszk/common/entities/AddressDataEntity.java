package pl.mariuszk.common.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "address_data")
public class AddressDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "mail", nullable = false, unique = true)
    private String mail;

    @Column(name = "street")
    private String street;

    @Column(name = "house_no")
    private String houseNo;

    @Column(name = "apartment_no")
    private String apartmentNo;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "city")
    private String city;
}
