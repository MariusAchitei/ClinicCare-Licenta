package ro.uaic.clinic_care.repository;

import ro.uaic.clinic_care.models.MedicInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicIntervalRepository extends JpaRepository<MedicInterval, Long> {

    List<MedicInterval> findMedicIntervalByMedicIdAndStartDateTimeAndEndDateTime(Long medicId, LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<MedicInterval> findMedicIntervalByMedicId(Long medicId);


    @Query("SELECT mi FROM MedicInterval mi " +
            "JOIN mi.medic m " +
            "JOIN m.departments dep " +
            "WHERE mi.medic.id IN :medicIds AND " +
            "dep.id IN :departmentIds AND " +
            "mi.startDateTime >= :startDateTime AND " +
            "mi.endDateTime <= :endDateTime")
    List<MedicInterval> findMedicIntervalsByFilter(List<Long> medicIds, List<Long> departmentIds, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
