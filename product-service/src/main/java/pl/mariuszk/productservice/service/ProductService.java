package pl.mariuszk.productservice.service;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import pl.mariuszk.productservice.elasticsearch.model.ProductElastic;
import pl.mariuszk.productservice.request.ProductRequest;

import java.util.List;

import static java.util.Objects.nonNull;
import static org.elasticsearch.index.query.QueryBuilders.*;
import static pl.mariuszk.productservice.enums.ElasticsearchFields.*;
import static pl.mariuszk.productservice.enums.ElasticsearchIndexes.PRODUCT_INDEX;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final PagingService pagingService;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public Page<ProductElastic> getProductsList(ProductRequest request) {
        Pageable pageable = pagingService.buildPageableFromRequest(request);

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(getBoolQueryBuilderForProductList(request));
        nativeSearchQueryBuilder.withPageable(pageable);

        SearchHits<ProductElastic> searchHits = elasticsearchRestTemplate.search(nativeSearchQueryBuilder.build(),
                ProductElastic.class, IndexCoordinates.of(PRODUCT_INDEX.getName()));

        List<ProductElastic> productList = searchHits.get().map(SearchHit::getContent).toList();

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
        if (nonNull(request.getPriceMin()) && nonNull(request.getPriceMax())) {
            boolQueryBuilder.must(rangeQuery(PRICE.getName()).gte(request.getPriceMin()).lte(request.getPriceMax()));
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
}
