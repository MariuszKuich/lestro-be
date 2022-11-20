package pl.mariuszk.productservice.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pl.mariuszk.elasticsearch.model.ConfiguratorElementElastic;

public interface ConfiguratorRepository extends ElasticsearchRepository<ConfiguratorElementElastic, String> {

}
