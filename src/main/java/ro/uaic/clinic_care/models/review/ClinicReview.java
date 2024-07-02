package ro.uaic.clinic_care.models.review;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import ro.uaic.clinic_care.models.BaseEntity;
import ro.uaic.clinic_care.models.Patient;
import ro.uaic.clinic_care.models.administrative.Clinic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class ClinicReview extends BaseEntity {

    private String content;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime date;
}
