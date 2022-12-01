package pl.mariuszk.orderservice.elasticsearch.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pl.mariuszk.elasticsearch.model.ProductOrderElastic;

import java.util.List;

public interface ProductOrderRepository extends ElasticsearchRepository<ProductOrderElastic, String> {

    List<ProductOrderElastic> findByOrderNumber(long orderNumber);

    List<ProductOrderElastic> findByOrderNumber(long orderNumber, Sort sort);
}
