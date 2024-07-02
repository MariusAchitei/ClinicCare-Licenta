package ro.uaic.clinic_care.models.administrative;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import ro.uaic.clinic_care.models.BaseEntity;
import ro.uaic.clinic_care.models.Medic;

@Entity
@Data
@Builder
public class MedicDepartment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "medic_id")
    private Medic medic;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String role;
}
