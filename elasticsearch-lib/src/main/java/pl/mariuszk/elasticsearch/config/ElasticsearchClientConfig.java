package pl.mariuszk.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

public abstract class ElasticsearchClientConfig {

    public static final int MAX_ES_PAGE_SIZE = 10_000;

    private static final String ES_CLUSTER_ADDRESS = "localhost:9200";

    public static RestHighLevelClient getElasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(ES_CLUSTER_ADDRESS)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
