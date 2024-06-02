package ro.uaic.clinic_care.repository;

import ro.uaic.clinic_care.models.Medic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicRepository extends JpaRepository<Medic, Long> {
}
