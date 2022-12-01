package pl.mariuszk.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DeliveryCode {

    SELF_PICKUP("Odbiór osobisty"),
    PDP("Kurier PDP");

    private final String name;
}
