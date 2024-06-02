package ro.uaic.clinic_care.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class MedicDepartment extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "medic_id")
    private Medic medic;

    @ManyToOne
    @JoinColumn(name = "department_id")

    private Department department;
    private String role;
}
