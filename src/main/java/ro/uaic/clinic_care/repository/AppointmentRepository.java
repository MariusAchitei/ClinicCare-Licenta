package ro.uaic.clinic_care.repository;

import ro.uaic.clinic_care.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAppointmentByMedicIdAndStartDateTimeAndEndDateTime(Long medicId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Appointment> findByPatientId(Long patientId);

}
