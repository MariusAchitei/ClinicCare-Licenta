package ro.uaic.clinic_care.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicInterval extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "medic_id")
    private Medic medic;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

}
