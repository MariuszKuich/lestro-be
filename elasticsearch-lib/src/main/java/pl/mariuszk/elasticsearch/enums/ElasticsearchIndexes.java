package pl.mariuszk.elasticsearch.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ElasticsearchIndexes {
    PRODUCT_INDEX("product-index");

    private final String name;
}
