package ro.uaic.clinic_care.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Appointment parent;

    @OneToOne
    @JoinColumn(name = "diagnostic_id")
    private Diagnostic diagnostic;

    @OneToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;

    @ManyToOne
    @JoinColumn(name = "treatment_plan_id")
    private TreatmentPlan treatmentPlan;

    @ManyToOne
    @JoinColumn(name = "medic_interval_id")
    private MedicInterval medicInterval;

    @OneToMany
    private List<AppointmentSymptom> symptoms;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;


    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medic_id")
    private Medic medic;

    private String reason;

    private String status;

    private String recommendation;
    private String recommendationMap;


}
