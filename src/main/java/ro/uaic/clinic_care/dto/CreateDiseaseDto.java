package ro.uaic.clinic_care.dto;

import lombok.Data;

@Data
public class CreateDiseaseDto {
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
