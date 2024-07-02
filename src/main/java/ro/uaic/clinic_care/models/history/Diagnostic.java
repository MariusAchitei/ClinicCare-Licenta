package ro.uaic.clinic_care.models.history;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.uaic.clinic_care.models.BaseEntity;
import ro.uaic.clinic_care.models.Medic;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Diagnostic extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "disease_id")
    private Disease disease;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "treatment_plan_id")
    private TreatmentPlan treatmentPlan;

    @ManyToMany
    @JoinTable(name = "diagnostic_symptoms", joinColumns = @JoinColumn(name = "diagnostic_id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id"))
    private List<Symptom> symptoms;

    private String description;

    private LocalDateTime date;

    @OneToOne
    @JoinColumn(name = "parent_id")
    private Diagnostic parentDiagnostic;

    @ManyToOne
    @JoinColumn(name = "medic_id")
    private Medic medic;

    private String status;

    private String diagnosticType;


}
