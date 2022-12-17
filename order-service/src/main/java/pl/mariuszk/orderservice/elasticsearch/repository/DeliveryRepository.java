package pl.mariuszk.orderservice.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pl.mariuszk.common.enums.DeliveryCode;
import pl.mariuszk.elasticsearch.model.DeliveryElastic;

import java.util.Optional;

public interface DeliveryRepository extends ElasticsearchRepository<DeliveryElastic, String> {

    Optional<DeliveryElastic> findByCode(DeliveryCode code);
}
