package ro.uaic.clinic_care.dto.appointment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMedicationDrugDto {

    private Long drugId;

    private String dosage;

    private String frequency;

    private String duration;

    private String instructions;
}
