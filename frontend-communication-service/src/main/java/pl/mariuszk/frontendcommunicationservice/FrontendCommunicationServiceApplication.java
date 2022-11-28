package pl.mariuszk.frontendcommunicationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import pl.mariuszk.frontendcommunicationservice.config.RsaKeyProperties;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(RsaKeyProperties.class)
@EntityScan("pl.mariuszk.common.entities")
public class FrontendCommunicationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontendCommunicationServiceApplication.class, args);
    }

}
