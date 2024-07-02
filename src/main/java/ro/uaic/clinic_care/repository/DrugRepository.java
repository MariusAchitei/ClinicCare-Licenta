package ro.uaic.clinic_care.repository;

import ro.uaic.clinic_care.models.history.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug, Long> {
}
