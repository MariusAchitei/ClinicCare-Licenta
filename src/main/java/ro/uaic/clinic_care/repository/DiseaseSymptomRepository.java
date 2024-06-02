package ro.uaic.clinic_care.repository;

import ro.uaic.clinic_care.models.DiseaseSymptom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseSymptomRepository extends JpaRepository<DiseaseSymptom, Long> {
}
