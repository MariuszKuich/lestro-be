package pl.mariuszk.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public abstract class ElasticsearchClientConfig {

    public static final int MAX_ES_PAGE_SIZE = 10_000;

    private static final String ES_CLUSTER_ADDRESS = "localhost:9200";

    public static RestHighLevelClient getElasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(ES_CLUSTER_ADDRESS)
                .withDefaultHeaders(getDefaultHeaders())
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    // When using Spring Data Elasticsearch 4 - which uses the Elasticsearch 7 client libraries - and accessing
    // an Elasticsearch cluster that is running on version 8, it is necessary to set the compatibility headers
    // https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/
    private static HttpHeaders getDefaultHeaders() {
        MultiValueMap<String, String> headersMap = new LinkedMultiValueMap<>();
        headersMap.add(HttpHeaders.ACCEPT, "application/vnd.elasticsearch+json;compatible-with=7");
        headersMap.add(HttpHeaders.CONTENT_TYPE, "application/vnd.elasticsearch+json;compatible-with=7");
        return new HttpHeaders(headersMap);
    }
}
