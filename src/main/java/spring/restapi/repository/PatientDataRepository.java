package spring.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.restapi.model.PatientData;

public interface PatientDataRepository extends JpaRepository<PatientData, Long> {
}
