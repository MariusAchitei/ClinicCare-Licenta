package ro.uaic.clinic_care.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.clinic_care.models.administrative.Clinic;

import java.util.Optional;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    Optional<Clinic> findByName(String clinicName);
}
