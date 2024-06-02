package ro.uaic.clinic_care.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @ManyToMany
    @JoinTable(name = "diagnostic_symptoms", joinColumns = @JoinColumn(name = "diagnostic_id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id"))
    private List<Symptom> symptoms;

    private String description;

    private LocalDateTime diagnosticDate;

    @OneToOne
    @JoinColumn(name = "parent_id")
    private Diagnostic parentDiagnostic;

    @ManyToOne
    @JoinColumn(name = "medic_id")
    private Medic medic;

    private String status;

    private String diagnosticType;


}
