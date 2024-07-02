package ro.uaic.clinic_care.models.history;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import ro.uaic.clinic_care.models.BaseEntity;

@Data
@Entity
@AllArgsConstructor
public class Symptom extends BaseEntity {
    private String name;
    private String description;
}
