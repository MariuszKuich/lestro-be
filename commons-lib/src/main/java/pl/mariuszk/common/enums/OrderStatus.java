package pl.mariuszk.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    NEW("Nowe"),
    PAID("Opłacone"),
    IN_PROGRESS("W trakcie tralizacji"),
    SENT("Wysłane"),
    RECEIVED("Odebrane");

    private final String name;
}
