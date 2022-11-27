package pl.mariuszk.customerservice.model.frontend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    @NotBlank
    @Size(max = 20)
    private String name;
    @NotBlank
    @Size(max = 30)
    private String surname;
    @NotBlank
    @Email
    @Size(max = 50)
    private String mail;
    @NotBlank
    @Size(min = 8)
    private String password;
}
