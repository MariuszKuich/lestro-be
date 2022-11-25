package pl.mariuszk.paymentservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "payment")
public class ConfigProperties {

    private String newPaymentUrl;
}