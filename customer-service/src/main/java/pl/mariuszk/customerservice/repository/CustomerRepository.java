package pl.mariuszk.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mariuszk.common.entities.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    boolean existsByMail(String mail);
}
