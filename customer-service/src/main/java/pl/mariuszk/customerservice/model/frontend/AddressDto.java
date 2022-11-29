package pl.mariuszk.customerservice.model.frontend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private String mail;
    @Size(max = 30)
    private String street;
    @Size(max = 5)
    private String houseNo;
    @Size(max = 5)
    private String apartmentNo;
    @Size(max = 6)
    private String zipCode;
    @Size(max = 30)
    private String city;
}
