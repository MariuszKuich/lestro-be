package pl.mariuszk.paymentservice.service;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.mariuszk.paymentservice.elasticsearch.repository.PaymentRepository;
import pl.mariuszk.paymentservice.model.frontend.PaymentDto;

import java.util.List;

import static pl.mariuszk.elasticsearch.enums.ElasticsearchField.NAME;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final MapperFacade mapperFacade;

    public List<PaymentDto> getAvailablePaymentMethods() {
        return paymentRepository.findAll(Sort.by(NAME.getName()).ascending()).stream()
                .map(paymentElastic -> mapperFacade.map(paymentElastic, PaymentDto.class))
                .toList();
    }
}
