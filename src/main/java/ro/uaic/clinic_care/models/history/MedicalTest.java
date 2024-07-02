package ro.uaic.clinic_care.models.history;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.uaic.clinic_care.models.BaseEntity;
import ro.uaic.clinic_care.models.administrative.Department;
import ro.uaic.clinic_care.models.MedicalTestType;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalTest extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "treatment_plan_id")
    private TreatmentPlan treatmentPlan;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
    private MedicalTestType medicalTestType;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String result;
}
