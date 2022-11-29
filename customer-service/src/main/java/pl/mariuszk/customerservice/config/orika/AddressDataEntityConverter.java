package pl.mariuszk.customerservice.config.orika;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import pl.mariuszk.common.entities.AddressDataEntity;
import pl.mariuszk.customerservice.model.frontend.SavedAddressDto;

public class AddressDataEntityConverter extends CustomConverter<AddressDataEntity, SavedAddressDto>  {

    @Override
    public SavedAddressDto convert(AddressDataEntity addressDataEntity, Type<? extends SavedAddressDto> type, MappingContext mappingContext) {
        return SavedAddressDto.builder()
                .name(addressDataEntity.getName())
                .surname(addressDataEntity.getSurname())
                .mail(addressDataEntity.getMail())
                .street(addressDataEntity.getStreet())
                .houseNo(addressDataEntity.getHouseNo())
                .apartmentNo(addressDataEntity.getApartmentNo())
                .zipCode(addressDataEntity.getZipCode())
                .city(addressDataEntity.getCity())
                .build();
    }
}
