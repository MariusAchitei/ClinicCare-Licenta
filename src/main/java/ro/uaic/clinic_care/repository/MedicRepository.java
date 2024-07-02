package ro.uaic.clinic_care.repository;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.TSFBuilder;
import org.springframework.data.jpa.repository.Query;
import ro.uaic.clinic_care.models.Medic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicRepository extends JpaRepository<Medic, Long> {
    @Query("SELECT COUNT(m) FROM Medic m WHERE m.clinic.id = ?1")
    Integer countMedicsByClinicId(Long id);
}
