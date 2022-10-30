package pl.mariuszk.productservice.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pl.mariuszk.productservice.elasticsearch.model.ProductElastic;

public interface ProductRepository extends ElasticsearchRepository<ProductElastic, String> {

}
