package pl.mariuszk.productservice.elasticsearch.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pl.mariuszk.elasticsearch.model.ProductElastic;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends ElasticsearchRepository<ProductElastic, String> {

    Optional<ProductElastic> findByCode(String code);

    List<ProductElastic> findAll(Sort sort);

    void deleteByCode(String code);

    ProductElastic findTopByOrderByNoDesc();
}
