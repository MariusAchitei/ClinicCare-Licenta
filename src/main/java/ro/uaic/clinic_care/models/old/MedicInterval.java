package ro.uaic.clinic_care.models.old;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.uaic.clinic_care.models.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicInterval extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "medic_id")
    private Medicc medic;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

}
