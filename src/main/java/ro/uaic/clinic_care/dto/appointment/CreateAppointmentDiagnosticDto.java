package ro.uaic.clinic_care.dto.appointment;

import lombok.Data;

import java.util.List;

@Data
public class CreateAppointmentDiagnosticDto {
    private Long TreatmentPlanId;
    private Long medicId;
    private Long appointmentId;
    private List<Long> diseaseIds;
    private String description;
}
