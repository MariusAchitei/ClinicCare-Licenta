package ro.uaic.clinic_care.repository;

import ro.uaic.clinic_care.models.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
}
