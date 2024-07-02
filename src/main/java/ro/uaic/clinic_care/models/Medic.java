package ro.uaic.clinic_care.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.uaic.clinic_care.models.administrative.Clinic;
import ro.uaic.clinic_care.models.administrative.MedicDepartment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medic extends BaseEntity {

    @Embedded
    private Person person;

    private String professionalTitle;

    private Date employmentDate;

    private String university;

    //JSON
    private String bio;

    @OneToMany
    @JoinColumn(name = "medic_id")
    private List<MedicDepartment> departments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    private String title;

    private String photo;

    private Long externalId;

}
