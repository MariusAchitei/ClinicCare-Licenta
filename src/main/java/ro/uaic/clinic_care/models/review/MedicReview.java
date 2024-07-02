package ro.uaic.clinic_care.models.review;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.uaic.clinic_care.models.BaseEntity;
import ro.uaic.clinic_care.models.Medic;
import ro.uaic.clinic_care.models.Patient;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicReview extends BaseEntity {

    private String content;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medic_id")
    private Medic medic;
}
