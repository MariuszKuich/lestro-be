package pl.mariuszk.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PaymentCode {

    TRAD_TRANSFER("Przelew tradycyjny"),
    PAY_Y("PayY");

    private final String name;
}
