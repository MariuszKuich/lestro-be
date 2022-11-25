package pl.mariuszk.paymentservice.service;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.mariuszk.paymentservice.elasticsearch.repository.PaymentRepository;
import pl.mariuszk.paymentservice.model.frontend.NewPaymentDataDto;
import pl.mariuszk.paymentservice.model.frontend.PaymentDto;

import java.net.URI;
import java.util.List;

import static pl.mariuszk.elasticsearch.enums.ElasticsearchField.NAME;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final MapperFacade mapperFacade;

    @Value("${payment.newPaymentUrl}")
    private String newPaymentUrl;

    public List<PaymentDto> getAvailablePaymentMethods() {
        return paymentRepository.findAll(Sort.by(NAME.getName()).ascending()).stream()
                .map(paymentElastic -> mapperFacade.map(paymentElastic, PaymentDto.class))
                .toList();
    }

    public String sendNewPaymentToPaymentServiceAndObtainRedirectUrl(NewPaymentDataDto newPaymentDataDto) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(URI.create(newPaymentUrl), newPaymentDataDto, String.class);
    }
}
