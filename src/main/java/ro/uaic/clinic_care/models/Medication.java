package ro.uaic.clinic_care.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medication extends  BaseEntity{
    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    private String recommendations;

    @OneToMany
    @JoinColumn(name = "medication_id")
    private List<MedicationDrug> medicationDrugs;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
