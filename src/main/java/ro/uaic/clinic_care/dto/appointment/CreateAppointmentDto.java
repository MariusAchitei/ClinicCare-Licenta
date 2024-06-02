package ro.uaic.clinic_care.dto.appointment;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Data
public class CreateAppointmentDto {
    private Long parentAppointmentId;
    private Long medicId;
    private Long patientId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String reason;
    private List<String> symptoms;
}
