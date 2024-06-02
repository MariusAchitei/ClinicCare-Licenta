package ro.uaic.clinic_care.dto.appointment;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CreateMedicationDto {
    private Long appointmentId;

    private String recommendations;

    private List<CreateMedicationDrugDto> medicationDrugs;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
