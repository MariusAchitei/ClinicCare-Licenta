package ro.uaic.clinic_care.models.history;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import ro.uaic.clinic_care.models.BaseEntity;

@Entity
@Data
@Builder
public class Disease extends BaseEntity {
    private String name;
    private String description;
    private String symptoms;
    private String causes;
    private String riskFactors;
    private String complications;
    private String prevention;
    private String diagnosis;
    private String treatment;
    private String lifestyleAndHomeRemedies;
    private String copingAndSupport;
    private String preparingForYourAppointment;
    private String testsAndDiagnosis;
    private String treatmentsAndDrugs;
    private String alternativeMedicine;
    private String clinicalTrials;
    private String bodySystemsAffected;
}
