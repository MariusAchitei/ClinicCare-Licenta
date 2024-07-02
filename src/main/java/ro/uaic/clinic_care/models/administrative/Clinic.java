package ro.uaic.clinic_care.models.administrative;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.uaic.clinic_care.models.BaseEntity;
import ro.uaic.clinic_care.models.location.City;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Clinic extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private String address;

    private String phone;

    private String email;

    private String website;

    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")

    private String mainImage;
    //JSON
    @Column(columnDefinition = "text")

    private String gallery;

    private Double latitude;

    private Double longitude;


    //JSON List
    @Column(columnDefinition = "text")

    private String options;
    @Column(columnDefinition = "text")

    private String services;
}
