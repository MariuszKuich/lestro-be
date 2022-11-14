package pl.mariuszk.orderservice.config.beans;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mariuszk.orderservice.config.orika.OrderDtoConverter;
import pl.mariuszk.orderservice.config.orika.OrderItemDtoConverter;
import pl.mariuszk.orderservice.elasticsearch.repository.ProductRepository;

@Configuration
@RequiredArgsConstructor
public class OrikaConfig {

    private final ProductRepository productRepository;

    @Bean
    public MapperFacade getMapperFacadeBean() {
        MapperFactory mapperFactory =  new DefaultMapperFactory.Builder().useAutoMapping(true).build();
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new OrderDtoConverter());
        converterFactory.registerConverter(new OrderItemDtoConverter(productRepository));
        return mapperFactory.getMapperFacade();
    }
}
