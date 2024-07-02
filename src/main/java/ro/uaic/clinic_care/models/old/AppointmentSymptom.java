package ro.uaic.clinic_care.models.old;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import ro.uaic.clinic_care.models.BaseEntity;
import ro.uaic.clinic_care.models.history.Appointment;

@Entity
@Data
@Builder
public class AppointmentSymptom extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "symptom_id")
    private Appointment appointment;

    private String symptom;
}
