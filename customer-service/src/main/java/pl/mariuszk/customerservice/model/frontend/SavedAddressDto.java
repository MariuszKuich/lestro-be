package pl.mariuszk.customerservice.model.frontend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SavedAddressDto {

    private String name;
    private String surname;
    private String mail;
    private String street;
    private String houseNo;
    private String apartmentNo;
    private String zipCode;
    private String city;
}
