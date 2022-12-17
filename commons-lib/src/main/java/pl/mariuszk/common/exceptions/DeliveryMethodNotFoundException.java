package pl.mariuszk.common.exceptions;

public class DeliveryMethodNotFoundException extends RuntimeException {

    public DeliveryMethodNotFoundException() {
        super("delivery method with given code not found");
    }
}
