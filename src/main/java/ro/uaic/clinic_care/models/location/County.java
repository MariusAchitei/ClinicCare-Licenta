package ro.uaic.clinic_care.models.location;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import ro.uaic.clinic_care.models.BaseEntity;

import java.util.List;

@Entity
@Data
public class County extends BaseEntity {
    private String name;
    private String code;

     @OneToMany(mappedBy = "county")
    private List<City> cities;
}
