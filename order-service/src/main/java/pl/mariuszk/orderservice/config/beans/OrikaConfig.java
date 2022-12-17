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
import pl.mariuszk.orderservice.config.orika.OrderElasticConverter;
import pl.mariuszk.orderservice.config.orika.OrderItemDtoConverter;
import pl.mariuszk.orderservice.config.orika.OrderPanelDtoConverter;
import pl.mariuszk.orderservice.service.ConverterService;
import pl.mariuszk.orderservice.service.OrderItemsService;

@Configuration
@RequiredArgsConstructor
public class OrikaConfig {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private final OrderItemsService orderItemsService;
    private final ConverterService converterService;

    @Bean
    public MapperFacade getMapperFacadeBean() {
        MapperFactory mapperFactory =  new DefaultMapperFactory.Builder().useAutoMapping(true).build();
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new OrderDtoConverter());
        converterFactory.registerConverter(new OrderItemDtoConverter(elasticsearchRestTemplate));
        converterFactory.registerConverter(new OrderElasticConverter(orderItemsService, converterService));
        converterFactory.registerConverter(new OrderPanelDtoConverter(orderItemsService, converterService));
        return mapperFactory.getMapperFacade();
    }
}
