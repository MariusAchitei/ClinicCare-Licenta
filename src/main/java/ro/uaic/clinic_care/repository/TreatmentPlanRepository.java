package ro.uaic.clinic_care.repository;

import ro.uaic.clinic_care.models.history.TreatmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan, Long> {
}
