package pl.mariuszk.customerservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mariuszk.common.entities.CustomerEntity;
import pl.mariuszk.common.exceptions.CustomerAlreadyExistsException;
import pl.mariuszk.customerservice.model.frontend.SignUpDto;
import pl.mariuszk.customerservice.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final MapperFacade mapperFacade;
    private final AddressDataService addressDataService;

    @Transactional
    public void createNewAccount(SignUpDto signUpDto) {
        if (customerAlreadyExists(signUpDto.getMail())) {
            log.error("Error during creating new account: user with mail {} already exists", signUpDto.getMail());
            throw new CustomerAlreadyExistsException();
        }

        CustomerEntity customerEntity = mapperFacade.map(signUpDto, CustomerEntity.class);
        customerRepository.save(customerEntity);

        addressDataService.initAddressData(customerEntity.getName(), customerEntity.getSurname(), customerEntity.getMail());
    }

    private boolean customerAlreadyExists(String mail) {
        return customerRepository.existsByMail(mail);
    }
}
