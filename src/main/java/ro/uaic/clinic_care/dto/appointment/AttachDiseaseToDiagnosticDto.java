package ro.uaic.clinic_care.dto.appointment;

import lombok.Data;

@Data
public class AttachDiseaseToDiagnosticDto {
    private Long appointmentId;
    private Long diagnosticId;
    private Long diseaseId;
    private String description;
}
