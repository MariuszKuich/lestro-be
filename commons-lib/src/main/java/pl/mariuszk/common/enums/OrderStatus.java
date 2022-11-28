package pl.mariuszk.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    NEW("NOWE"),
    PAID("OPŁACONE"),
    IN_PROGRESS("W TRAKCIE REALIZACJI"),
    SENT("WYSŁANE"),
    RECEIVED("ODEBRANE");

    private final String name;
}
