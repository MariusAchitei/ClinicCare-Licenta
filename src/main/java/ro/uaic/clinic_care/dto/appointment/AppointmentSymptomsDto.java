package ro.uaic.clinic_care.dto.appointment;

import lombok.Data;

import java.util.List;

@Data
public class AppointmentSymptomsDto {
    private Long appointmentId;
    private List<Long> symptomIds;
}
