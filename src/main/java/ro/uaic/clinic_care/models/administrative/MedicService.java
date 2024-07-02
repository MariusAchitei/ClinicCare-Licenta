package ro.uaic.clinic_care.models.administrative;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import ro.uaic.clinic_care.models.BaseEntity;
import ro.uaic.clinic_care.models.Medic;

import java.util.Date;

@Entity
@Data
@Builder
public class MedicService extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "medical_service_id")
    private MedicalService medicalService;

    @ManyToOne
    @JoinColumn(name = "medic_id")
    private Medic medic;

    private boolean enabled;

    private Date start;

    private Date end;

    private Double price;
}
