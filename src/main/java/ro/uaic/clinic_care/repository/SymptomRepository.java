package ro.uaic.clinic_care.repository;

import ro.uaic.clinic_care.models.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymptomRepository extends JpaRepository<Symptom, Long>{
}
