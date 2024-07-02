package ro.uaic.clinic_care.repository;

import ro.uaic.clinic_care.models.history.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long>{
}
