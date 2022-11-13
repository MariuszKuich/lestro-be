package pl.mariuszk.deliveryservice.config.beans;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrikaConfig {

    @Bean
    public MapperFacade getMapperFacadeBean() {
        return new DefaultMapperFactory.Builder().useAutoMapping(true).build().getMapperFacade();
    }
}
