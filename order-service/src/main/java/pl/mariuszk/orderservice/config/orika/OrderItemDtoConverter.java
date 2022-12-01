package pl.mariuszk.orderservice.config.orika;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import pl.mariuszk.common.enums.CompositionElemType;
import pl.mariuszk.common.exceptions.ProductNotFoundException;
import pl.mariuszk.elasticsearch.model.ConfiguratorElementElastic;
import pl.mariuszk.elasticsearch.model.ProductOrderElastic;
import pl.mariuszk.orderservice.model.ProductPriceDto;
import pl.mariuszk.orderservice.model.frontend.order.NewOrderItemDto;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static pl.mariuszk.common.constants.CustomProductConstants.CUSTOM_PRODUCT_CODE_PREFIX;
import static pl.mariuszk.common.enums.CompositionElemType.*;
import static pl.mariuszk.elasticsearch.enums.ElasticsearchField.*;
import static pl.mariuszk.elasticsearch.enums.ElasticsearchIndex.CONFIGURATOR_INDEX;
import static pl.mariuszk.elasticsearch.enums.ElasticsearchIndex.PRODUCT_INDEX;

@RequiredArgsConstructor
public class OrderItemDtoConverter extends CustomConverter<NewOrderItemDto, ProductOrderElastic> {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public ProductOrderElastic convert(NewOrderItemDto orderItemDto, Type<? extends ProductOrderElastic> type, MappingContext mappingContext) {
        if (orderItemDto.getProductCode().startsWith(CUSTOM_PRODUCT_CODE_PREFIX)) {
            return buildProductElasticForCustomItem(orderItemDto);
        }
        return buildProductElasticForStandardItem(orderItemDto);
    }

    private ProductOrderElastic buildProductElasticForStandardItem(NewOrderItemDto orderItemDto) {
        return ProductOrderElastic.builder()
                .quantity(orderItemDto.getQuantity())
                .code(orderItemDto.getProductCode())
                .name(orderItemDto.getName())
                .img(orderItemDto.getImg())
                .price(getProductPriceByCode(orderItemDto.getProductCode()))
                .build();
    }

    private double getProductPriceByCode(String productCode) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(new BoolQueryBuilder().must(termQuery(CODE.getName(), productCode)));
        nativeSearchQueryBuilder.withSourceFilter(new FetchSourceFilter(new String[] { PRICE.getName() }, null));

        SearchHit<ProductPriceDto> searchHit = elasticsearchRestTemplate.searchOne(nativeSearchQueryBuilder.build(),
                ProductPriceDto.class, IndexCoordinates.of(PRODUCT_INDEX.getName()));

        return Optional.ofNullable(searchHit)
                .map(sHit -> sHit.getContent().getPrice())
                .orElseThrow(ProductNotFoundException::new);
    }

    private ProductOrderElastic buildProductElasticForCustomItem(NewOrderItemDto orderItemDto) {
        ConfiguratorElementElastic plantData = getConfiguratorElementByCodeAndType(orderItemDto.getPlantCode(), PLANT);
        ConfiguratorElementElastic decorationData = getConfiguratorElementByCodeAndType(orderItemDto.getDecorationCode(), DECORATION);
        ConfiguratorElementElastic ornamentData = getConfiguratorElementByCodeAndType(orderItemDto.getOrnamentCode(), ORNAMENT);
        ConfiguratorElementElastic potData = getConfiguratorElementByCodeAndType(orderItemDto.getPotCode(), POT);

        return ProductOrderElastic.builder()
                .quantity(orderItemDto.getQuantity())
                .code(orderItemDto.getProductCode())
                .name(orderItemDto.getName())
                .img(orderItemDto.getImg())
                .price(sumPrices(plantData.getPrice(), decorationData.getPrice(), ornamentData.getPrice(), potData.getPrice()))
                .plantName(plantData.getName())
                .decorationName(decorationData.getName())
                .ornamentName(ornamentData.getName())
                .potName(potData.getName())
                .build();
    }

    private ConfiguratorElementElastic getConfiguratorElementByCodeAndType(String code, CompositionElemType type) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(
                new BoolQueryBuilder().must(termQuery(CODE.getName(), code)).must(termQuery(TYPE.getName(), type))
        );

        SearchHit<ConfiguratorElementElastic> searchHit = elasticsearchRestTemplate.searchOne(nativeSearchQueryBuilder.build(),
                ConfiguratorElementElastic.class, IndexCoordinates.of(CONFIGURATOR_INDEX.getName()));

        return Optional.ofNullable(searchHit)
                .map(SearchHit::getContent)
                .orElseThrow(ProductNotFoundException::new);
    }

    private double sumPrices(double plantPrice, double decorationPrice, double ornamentPrice, double potPrice) {
        return plantPrice + decorationPrice + ornamentPrice + potPrice;
    }
}
