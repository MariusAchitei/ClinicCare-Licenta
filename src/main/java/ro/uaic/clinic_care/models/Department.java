package ro.uaic.clinic_care.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Department extends BaseEntity
{

    private String name;

    private String description;

    @OneToMany
    @JoinColumn(name = "medic_id")
    private List<MedicDepartment> medics;

}
