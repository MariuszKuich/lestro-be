package pl.mariuszk.deliveryservice.elasticsearch.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pl.mariuszk.elasticsearch.model.DeliveryElastic;

import java.util.List;

public interface DeliveryRepository extends ElasticsearchRepository<DeliveryElastic, String> {

    List<DeliveryElastic> findAll(Sort sort);
}
