package pl.mariuszk.orderservice.elasticsearch.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pl.mariuszk.elasticsearch.model.OrderElastic;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends ElasticsearchRepository<OrderElastic, String> {

    Optional<OrderElastic> findTopByOrderByOrderNumberDesc();

    List<OrderElastic> findByMail(String mail, Sort sort);
}
