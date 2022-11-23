package pl.mariuszk.orderservice.config.beans;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import pl.mariuszk.orderservice.config.orika.OrderDtoConverter;
import pl.mariuszk.orderservice.config.orika.OrderItemDtoConverter;

@Configuration
@RequiredArgsConstructor
public class OrikaConfig {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Bean
    public MapperFacade getMapperFacadeBean() {
        MapperFactory mapperFactory =  new DefaultMapperFactory.Builder().useAutoMapping(true).build();
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new OrderDtoConverter());
        converterFactory.registerConverter(new OrderItemDtoConverter(elasticsearchRestTemplate));
        return mapperFactory.getMapperFacade();
    }
}
