package pl.mariuszk.customerservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import pl.mariuszk.common.exceptions.CustomerAlreadyExistsException;
import pl.mariuszk.customerservice.model.entity.CustomerEntity;
import pl.mariuszk.customerservice.model.frontend.SignUpDto;
import pl.mariuszk.customerservice.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final MapperFacade mapperFacade;

    public void createNewAccount(SignUpDto signUpDto) {
        if (customerAlreadyExists(signUpDto.getMail())) {
            log.error("Error during creating new account: user with mail {} already exists", signUpDto.getMail());
            throw new CustomerAlreadyExistsException();
        }

        CustomerEntity customerEntity = mapperFacade.map(signUpDto, CustomerEntity.class);
        customerRepository.save(customerEntity);
    }

    private boolean customerAlreadyExists(String mail) {
        return customerRepository.findByMail(mail).isPresent();
    }
}
