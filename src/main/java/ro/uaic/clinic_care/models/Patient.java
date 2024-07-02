package ro.uaic.clinic_care.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Patient extends BaseEntity {

    @Embedded
    private Person person;

    private String profession;
    private String bloodType;
    private String rh;

    //JSON
    private String bio;

    private Long externalId;

}
