package pl.mariuszk.orderservice.model.frontend.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDataDto {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    @Email
    private String mail;

    @NotBlank
    private String street;

    @NotBlank
    private String houseNo;

    private String apartNo;

    @NotBlank
    @Pattern(regexp = "\\d\\d-\\d\\d\\d")
    private String zipCode;

    @NotBlank
    private String city;
}
