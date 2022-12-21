package pl.mariuszk.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    NEW("Nowe", 1),
    PAID("Opłacone", 2),
    IN_PROGRESS("W trakcie realizacji", 3),
    SENT("Wysłane", 4),
    RECEIVED("Odebrane", 5);

    private final String name;
    private final int sequenceNumber;
}
