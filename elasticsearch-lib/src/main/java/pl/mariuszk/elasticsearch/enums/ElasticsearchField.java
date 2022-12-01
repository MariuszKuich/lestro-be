package pl.mariuszk.elasticsearch.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ElasticsearchField {
    CODE("code"),
    NAME("name"),
    PRICE("price"),
    PLANTS("plants"),
    HEIGHT("height"),
    WIDTH("width"),
    LENGTH("length"),
    IMGS("imgs"),
    TYPE("type"),
    CREATED_TIMESTAMP("created-timestamp");

    private final String name;
}
