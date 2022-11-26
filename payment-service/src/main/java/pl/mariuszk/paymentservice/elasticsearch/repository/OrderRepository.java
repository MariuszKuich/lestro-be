package pl.mariuszk.paymentservice.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pl.mariuszk.elasticsearch.model.OrderElastic;

import java.util.Optional;

public interface OrderRepository extends ElasticsearchRepository<OrderElastic, String> {

    Optional<OrderElastic> findByOrderNumberAndPaidFalse(long orderNumber);
}
