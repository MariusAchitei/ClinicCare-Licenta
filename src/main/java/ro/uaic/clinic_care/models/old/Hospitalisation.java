package ro.uaic.clinic_care.models.old;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.uaic.clinic_care.models.BaseEntity;
import ro.uaic.clinic_care.models.history.TreatmentPlan;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hospitalisation extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "treatmentPlan_id")
    private TreatmentPlan treatmentPlan;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

}
