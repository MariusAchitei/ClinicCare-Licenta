package ro.uaic.clinic_care.repository;

import ro.uaic.clinic_care.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
