package pl.mariuszk.common.exceptions;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException() {
        super("order with given number and payment status 'NOT_PAID' not found");
    }
}
