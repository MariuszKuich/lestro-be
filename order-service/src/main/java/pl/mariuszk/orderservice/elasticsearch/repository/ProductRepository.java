package pl.mariuszk.orderservice.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pl.mariuszk.elasticsearch.model.ProductElastic;

public interface ProductRepository extends ElasticsearchRepository<ProductElastic, String> {

    ProductElastic getByCode(String code);
}
