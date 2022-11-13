package pl.mariuszk.deliveryservice.service;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.mariuszk.deliveryservice.elasticsearch.repository.DeliveryRepository;
import pl.mariuszk.deliveryservice.model.frontend.DeliveryDto;

import java.util.List;

import static pl.mariuszk.elasticsearch.enums.ElasticsearchField.PRICE;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final MapperFacade mapperFacade;

    public List<DeliveryDto> getAvailableDeliveryMethods() {
        return deliveryRepository.findAll(Sort.by(PRICE.getName()).ascending()).stream()
                .map(deliveryElastic -> mapperFacade.map(deliveryElastic, DeliveryDto.class))
                .toList();
    }
}
