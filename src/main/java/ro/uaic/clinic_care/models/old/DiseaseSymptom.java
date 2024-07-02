package ro.uaic.clinic_care.models.old;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import ro.uaic.clinic_care.models.BaseEntity;
import ro.uaic.clinic_care.models.history.Disease;
import ro.uaic.clinic_care.models.history.Symptom;

@Data
@Entity(name = "disease_symptom")
public class DiseaseSymptom extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "disease_id")
    private Disease disease;

    @ManyToOne
    @JoinColumn(name = "symptom_id")
    private Symptom symptom;


    private String description;

}
