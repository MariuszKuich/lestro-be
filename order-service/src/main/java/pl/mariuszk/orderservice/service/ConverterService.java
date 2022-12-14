package pl.mariuszk.orderservice.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import pl.mariuszk.common.enums.OrderStatus;
import pl.mariuszk.elasticsearch.model.OrderElastic;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ConverterService {

    public String buildAddress(OrderElastic orderElastic) {
        String street = orderElastic.getStreet();
        String houseNo = orderElastic.getHouseNo();
        String apartmentNo = orderElastic.getApartmentNo();
        String zipCode = orderElastic.getZipCode();
        String city = orderElastic.getCity();

        if (StringUtils.isEmpty(apartmentNo)) {
            return String.format("%s %s, %s %s", street, houseNo, zipCode, city);
        }
        return String.format("%s %s/%s, %s %s", street, houseNo, apartmentNo, zipCode, city);
    }

    public String buildClientFullName(OrderElastic orderElastic) {
        return String.format("%s %s", orderElastic.getName(), orderElastic.getSurname());
    }

    public Map<String, String> getAvailableStatuses(OrderElastic orderElastic) {
        if (!orderElastic.isPaid()) {
            return Collections.emptyMap();
        }

        return Stream.of(OrderStatus.values())
                .filter(orderStatus -> orderStatus.getSequenceNumber() > orderElastic.getStatus().getSequenceNumber())
                .collect(Collectors.toMap(
                        OrderStatus::name,
                        OrderStatus::getName,
                        (o1, o2) -> {
                            throw new IllegalStateException(String.format("Duplicate key %s", o1));
                        },
                        LinkedHashMap::new));
    }
}
