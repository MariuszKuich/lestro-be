package pl.mariuszk.customerservice.config.orika;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.mariuszk.customerservice.model.entity.CustomerEntity;
import pl.mariuszk.customerservice.model.frontend.SignUpDto;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class SignUpDtoConverter extends CustomConverter<SignUpDto, CustomerEntity> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public CustomerEntity convert(SignUpDto signUpDto, Type<? extends CustomerEntity> type, MappingContext mappingContext) {
        return CustomerEntity.builder()
                .name(signUpDto.getName())
                .surname(signUpDto.getSurname())
                .mail(signUpDto.getMail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .createdAt(LocalDateTime.now())
                .build();
    }
}
