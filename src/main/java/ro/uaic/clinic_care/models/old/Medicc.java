package ro.uaic.clinic_care.models.old;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.uaic.clinic_care.models.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medicc extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany
    @JoinColumn(name = "medic_id")
    private List<MedicDepartment> departments = new ArrayList<>();
    private String title;

}
