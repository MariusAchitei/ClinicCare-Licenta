package ro.uaic.clinic_care.models.history;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.uaic.clinic_care.models.BaseEntity;
import ro.uaic.clinic_care.models.Patient;
import ro.uaic.clinic_care.models.administrative.MedicService;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany
    private List<AppointmentSymptom> symptoms;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;


    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medic_service_id")
    private MedicService medicService;

    private String reason;

    private String status;

    //JSON
    private String recommendation;
    private String recommendationMap;

    //LINK LA S3
    private String additionalDataLocation;


}
