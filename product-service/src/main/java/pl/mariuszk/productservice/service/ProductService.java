package pl.mariuszk.productservice.service;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import pl.mariuszk.productservice.elasticsearch.model.ProductElastic;
import pl.mariuszk.productservice.elasticsearch.repository.ProductRepository;
import pl.mariuszk.productservice.model.frontend.ProductDetailsDto;
import pl.mariuszk.productservice.model.frontend.ProductDto;
import pl.mariuszk.productservice.request.ProductRequest;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static org.elasticsearch.index.query.QueryBuilders.*;
import static pl.mariuszk.productservice.elasticsearch.config.ElasticsearchConfig.MAX_ES_PAGE_SIZE;
import static pl.mariuszk.productservice.enums.ElasticsearchFields.*;
import static pl.mariuszk.productservice.enums.ElasticsearchIndexes.PRODUCT_INDEX;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final PagingService pagingService;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private final ProductRepository productRepository;
    private final MapperFacade mapperFacade;

    public Page<ProductDto> getProductsList(ProductRequest request) {
        Pageable pageable = pagingService.buildPageableFromRequest(request);

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(getBoolQueryBuilderForProductList(request));
        nativeSearchQueryBuilder.withPageable(pageable);
        nativeSearchQueryBuilder.withSourceFilter(new FetchSourceFilter(
                new String[] { NAME.getName(), CODE.getName(), PRICE.getName(), IMGS.getName() }, null));

        SearchHits<ProductDto> searchHits = elasticsearchRestTemplate.search(nativeSearchQueryBuilder.build(),
                ProductDto.class, IndexCoordinates.of(PRODUCT_INDEX.getName()));

        List<ProductDto> productList = searchHits.get().map(SearchHit::getContent).toList();

        return new PageImpl<>(productList, pageable, searchHits.getTotalHits());
    }

    private BoolQueryBuilder getBoolQueryBuilderForProductList(ProductRequest request) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        if (nonNull(request.getCode())) {
            boolQueryBuilder.must(termQuery(CODE.getName(), request.getCode()));
        }
        if (nonNull(request.getName())) {
            boolQueryBuilder.must(wildcardQuery(NAME.getName(), String.format("*%s*", request.getName())));
        }
        if (nonNull(request.getPriceMin()) || nonNull(request.getPriceMax())) {
            boolQueryBuilder.must(buildPriceRangeQuery(request));
        }
        if (nonNull(request.getPlants())) {
            boolQueryBuilder.must(termsQuery(PLANTS.getName(), request.getPlants()));
        }
        if (nonNull(request.getHeightMax())) {
            boolQueryBuilder.must(rangeQuery(HEIGHT.getName()).lte(request.getHeightMax()));
        }
        if (nonNull(request.getWidthMax())) {
            boolQueryBuilder.must(rangeQuery(WIDTH.getName()).lte(request.getWidthMax()));
        }
        if (nonNull(request.getLengthMax())) {
            boolQueryBuilder.must(rangeQuery(LENGTH.getName()).lte(request.getLengthMax()));
        }

        return boolQueryBuilder;
    }

    private RangeQueryBuilder buildPriceRangeQuery(ProductRequest request) {
        RangeQueryBuilder priceRangeQuery = rangeQuery(PRICE.getName());
        Optional.ofNullable(request.getPriceMin()).ifPresent(priceRangeQuery::gte);
        Optional.ofNullable(request.getPriceMax()).ifPresent(priceRangeQuery::lte);
        return priceRangeQuery;
    }

    public List<String> getGroupedPlantsFromEveryProduct() {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withSourceFilter(new FetchSourceFilter(new String[] { PLANTS.getName() }, null));
        nativeSearchQueryBuilder.withPageable(Pageable.ofSize(MAX_ES_PAGE_SIZE));

        SearchHits<ProductElastic> searchHits = elasticsearchRestTemplate.search(nativeSearchQueryBuilder.build(),
                ProductElastic.class, IndexCoordinates.of(PRODUCT_INDEX.getName()));

        return searchHits.get()
                .map(SearchHit::getContent)
                .map(ProductElastic::getPlants)
                .flatMap(List::stream)
                .distinct()
                .sorted()
                .toList();
    }

    public Optional<ProductDetailsDto> getProductDetails(String code) {
        return productRepository.findByCode(code)
                .map(productDetails -> mapperFacade.map(productDetails, ProductDetailsDto.class));
    }
}
