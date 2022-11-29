package pl.mariuszk.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mariuszk.common.entities.AddressDataEntity;

import java.util.Optional;

public interface AddressDataRepository extends JpaRepository<AddressDataEntity, Long> {

    Optional<AddressDataEntity> findByMail(String mail);
}
