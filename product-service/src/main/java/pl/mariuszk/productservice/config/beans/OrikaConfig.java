package pl.mariuszk.productservice.config.beans;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mariuszk.productservice.config.orika.NewProductDtoConverter;
import pl.mariuszk.productservice.elasticsearch.repository.ProductRepository;

@RequiredArgsConstructor
@Configuration
public class OrikaConfig {

    private final ProductRepository productRepository;

    @Bean
    public MapperFacade getMapperFacadeBean() {
        MapperFactory mapperFactory =  new DefaultMapperFactory.Builder().useAutoMapping(true).build();
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new NewProductDtoConverter(productRepository));
        return mapperFactory.getMapperFacade();
    }
}
