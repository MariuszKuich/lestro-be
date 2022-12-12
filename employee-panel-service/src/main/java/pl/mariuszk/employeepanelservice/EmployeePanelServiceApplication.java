package pl.mariuszk.employeepanelservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.mariuszk.employeepanelservice.config.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class EmployeePanelServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeePanelServiceApplication.class, args);
    }

}
