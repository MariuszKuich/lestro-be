package pl.mariuszk.employeepanelservice.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mariuszk.common.enums.Authority;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mail", nullable = false, unique = true)
    private String mail;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "authority", nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;
}
