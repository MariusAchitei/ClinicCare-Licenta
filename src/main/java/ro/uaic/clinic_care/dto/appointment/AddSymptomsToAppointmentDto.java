package ro.uaic.clinic_care.dto.appointment;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AddSymptomsToAppointmentDto {
    Long appointmentId;
    List<Long> symptomIds;
    Map<String, String> customSymptoms;
}
