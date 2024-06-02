package ro.uaic.clinic_care.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class AppointmentSymptom extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "symptom_id")
    private Appointment appointment;

    private String symptom;
}
