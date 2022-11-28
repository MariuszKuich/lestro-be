package pl.mariuszk.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Authority {
    CUSTOMER("SCOPE_CUSTOMER"),
    EMPLOYEE("SCOPE_EMPLOYEE");

    private final String scope;
}
