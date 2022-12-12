package pl.mariuszk.employeepanelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mariuszk.employeepanelservice.model.entities.EmployeeEntity;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findByMail(String mail);
}
