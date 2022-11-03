package pl.mariuszk.productservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ElasticsearchFields {
    CODE("code"),
    NAME("name"),
    PRICE("price"),
    PLANTS("plants"),
    HEIGHT("height"),
    WIDTH("width"),
    LENGTH("length"),
    IMG("img");

    private final String name;
}
