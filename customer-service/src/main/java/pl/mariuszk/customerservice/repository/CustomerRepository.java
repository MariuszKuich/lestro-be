package pl.mariuszk.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mariuszk.common.entities.CustomerEntity;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByMail(String mail);
}
