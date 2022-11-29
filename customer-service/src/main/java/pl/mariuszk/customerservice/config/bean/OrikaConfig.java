package pl.mariuszk.customerservice.config.bean;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.mariuszk.customerservice.config.orika.AddressDataEntityConverter;
import pl.mariuszk.customerservice.config.orika.SignUpDtoConverter;

@Configuration
@RequiredArgsConstructor
public class OrikaConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public MapperFacade getMapperFacadeBean() {
        MapperFactory mapperFactory =  new DefaultMapperFactory.Builder().useAutoMapping(true).build();
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new SignUpDtoConverter(passwordEncoder));
        converterFactory.registerConverter(new AddressDataEntityConverter());
        return mapperFactory.getMapperFacade();
    }
}
