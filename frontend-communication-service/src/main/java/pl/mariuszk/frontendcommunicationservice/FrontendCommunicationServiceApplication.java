package pl.mariuszk.frontendcommunicationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FrontendCommunicationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontendCommunicationServiceApplication.class, args);
    }

}
