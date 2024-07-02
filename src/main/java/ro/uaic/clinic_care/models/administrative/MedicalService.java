package ro.uaic.clinic_care.models.administrative;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import ro.uaic.clinic_care.models.BaseEntity;

@Entity
@Data
@Builder
public class MedicalService extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String description;

    private String duration;

    private String photoLocation;
}
