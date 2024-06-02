package ro.uaic.clinic_care.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDrug extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;

    @ManyToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;

    private String dosage;

    private String frequency;

    private String duration;

    private String instructions;
}
