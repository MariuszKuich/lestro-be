package pl.mariuszk.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AddressDataNotFoundException extends RuntimeException {

    public AddressDataNotFoundException() {
        super("address data for given e-mail address not found");
    }
}
