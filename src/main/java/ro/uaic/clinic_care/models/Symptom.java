package ro.uaic.clinic_care.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Symptom extends BaseEntity {
    private String name;
    private String description;
}
