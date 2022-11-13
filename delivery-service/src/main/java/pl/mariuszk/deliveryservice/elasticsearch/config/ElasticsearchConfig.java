package pl.mariuszk.deliveryservice.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import pl.mariuszk.elasticsearch.config.ElasticsearchClientConfig;

@Configuration
@EnableElasticsearchRepositories(basePackages = "pl.mariuszk.deliveryservice.elasticsearch.repository")
@ComponentScan(basePackages = "pl.mariuszk.deliveryservice.elasticsearch")
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        return ElasticsearchClientConfig.getElasticsearchClient();
    }

    @Bean
    public ElasticsearchRestTemplate elasticsearchRestTemplate() {
        return new ElasticsearchRestTemplate(this.elasticsearchClient());
    }
}
