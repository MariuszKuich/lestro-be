package pl.mariuszk.paymentservice.elasticsearch.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pl.mariuszk.elasticsearch.model.PaymentElastic;

import java.util.List;

public interface PaymentRepository extends ElasticsearchRepository<PaymentElastic, String> {

    List<PaymentElastic> findAll(Sort sort);
}
