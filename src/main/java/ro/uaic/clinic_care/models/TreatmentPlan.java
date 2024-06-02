package ro.uaic.clinic_care.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentPlan extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "parent_appointment_id")
    private Appointment parentAppointment;
    private String description;

    @ManyToOne
    @JoinColumn(name = "medic_id")
    private Medic medic;

    @OneToMany
    @JoinColumn(name = "treatment_plan_id")
    private List<Appointment> appointments = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "treatment_plan_id")
    private List<MedicalTest> medicalTests = new ArrayList<>();

}
