package pl.mariuszk.productservice.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@Getter
@Setter
@ConfigurationProperties(prefix = "elasticsearch")
@ConfigurationPropertiesScan
public class ElasticsearchProperties {

    private String clusterAddress;
}
