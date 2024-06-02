package ro.uaic.clinic_care.dto.appointment;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class CreateAppointmentRecomandationDto {
    Long appointmentId;
    String recommendations;
    Map<String, String> recommendationMap;
}
