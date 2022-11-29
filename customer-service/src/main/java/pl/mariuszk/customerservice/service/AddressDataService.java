package pl.mariuszk.customerservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import pl.mariuszk.common.entities.AddressDataEntity;
import pl.mariuszk.common.exceptions.AddressDataNotFoundException;
import pl.mariuszk.customerservice.model.frontend.AddressDto;
import pl.mariuszk.customerservice.model.frontend.SavedAddressDto;
import pl.mariuszk.customerservice.repository.AddressDataRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressDataService {

    private final AddressDataRepository addressDataRepository;
    private final MapperFacade mapperFacade;

    public void initAddressData(String name, String surname, String mail) {
        AddressDataEntity addressDataEntity = initializeAddressDataEntity(name, surname, mail);
        addressDataRepository.save(addressDataEntity);
    }

    private AddressDataEntity initializeAddressDataEntity(String name, String surname, String mail) {
        return AddressDataEntity.builder()
                .name(name)
                .surname(surname)
                .mail(mail)
                .build();
    }

    public void saveAddressData(AddressDto addressDto) {
        AddressDataEntity addressDataEntity = getAddressDataOrThrow(addressDto.getMail());
        updateExistingAddressData(addressDataEntity, addressDto);
    }

    private AddressDataEntity getAddressDataOrThrow(String mail) {
        return addressDataRepository.findByMail(mail).orElseThrow(() -> {
            log.error("Address data for {} not found", mail);
            throw new AddressDataNotFoundException();
        });
    }

    private void updateExistingAddressData(AddressDataEntity savedAddressData, AddressDto newAddressData) {
        savedAddressData.setStreet(newAddressData.getStreet());
        savedAddressData.setHouseNo(newAddressData.getHouseNo());
        savedAddressData.setApartmentNo(newAddressData.getApartmentNo());
        savedAddressData.setZipCode(newAddressData.getZipCode());
        savedAddressData.setCity(newAddressData.getCity());

        addressDataRepository.save(savedAddressData);
    }

    public SavedAddressDto getAddressData(String customerMail) {
        AddressDataEntity addressDataEntity = getAddressDataOrThrow(customerMail);
        return mapperFacade.map(addressDataEntity, SavedAddressDto.class);
    }
}
