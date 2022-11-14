package pl.mariuszk.orderservice.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pl.mariuszk.elasticsearch.model.ProductOrderElastic;

public interface ProductOrderRepository extends ElasticsearchRepository<ProductOrderElastic, String> {
}
