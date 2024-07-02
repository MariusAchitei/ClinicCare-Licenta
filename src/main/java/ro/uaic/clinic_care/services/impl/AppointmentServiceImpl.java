package ro.uaic.clinic_care.services.impl;

import ro.uaic.clinic_care.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.clinic_care.dto.appointment.*;
import ro.uaic.clinic_care.models.Medic;
import ro.uaic.clinic_care.models.history.*;
import ro.uaic.clinic_care.repository.*;
import ro.uaic.clinic_care.utils.ConvertUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl {

    private final AppointmentRepository appointmentRepository;
    private final MedicIntervalRepository medicIntervalRepository;
    private final MedicRepository medicRepository;
    private final PatientRepository patientRepository;
    private final MedicIntervalServiceImpl medicIntervalService;
    private final TreatmentPlanRepository treatmentPlanRepository;
    private final SymptomServiceImpl symptomService;
    private final DiagnosticRepository diagnosticRepository;
    private final DiseaseServiceImpl diseaseService;
    private final DrugRepository drugRepository;
    private final MedicationRepository medicationRepository;

    public AppointmentDTO getAppointmentDetails(Long id){
        Appointment appointment = appointmentRepository.findById(id).orElseThrow();

        AppointmentDTO appointmentDTO = new AppointmentDTO();
        // Map fields from appointment to appointmentDTO
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setReason(appointment.getReason());
        appointmentDTO.setDate(appointment.getStartDateTime().toLocalDate());
        appointmentDTO.setPhoto(appointment.getMedicService().getPhotoLocation());
        appointmentDTO.setStartTime(appointmentDTO.getStartTime());
        appointmentDTO.setEndTime(appointmentDTO.getEndTime());
        appointmentDTO.setDuration(appointmentDTO.getDuration());
        appointmentDTO.setMarkedDate(new Date());
        appointmentDTO.setRecommendations(appointment.getRecommendation());
        appointmentDTO.setClinic(appointment.getMedicService().getMedic().getClinic().getName());
        appointmentDTO.setParentAppointment("Annual Checkup");
        appointmentDTO.setStatus(appointment.getStatus());

        // Set nested objects
        // Populate TreatmentPlanDTO, MedicDTO, ServicesDTO, etc.

        // Example for MedicDTO
        AppointmentDTO.MedicDTO medicDTO = new AppointmentDTO.MedicDTO();
        Medic medic = appointment.getMedicService().getMedic();
        medicDTO.setName(medic.getPerson().getFirstName() + " " + medic.getPerson().getLastName());
        medicDTO.setSpecialty(medic.getDepartments().get(0).getDepartment().getName());
        medicDTO.setLocation(medic.getClinic().getName());
        // Add other fields and nested objects

        appointmentDTO.setMedic(medicDTO);

        // Set Files
        appointmentDTO.setFiles(List.of(
                new AppointmentDTO.FileDTO(1L, "Medical Report.pdf", "pdf", "/api/download?fileName=MedicalReport.pdf"),
                new AppointmentDTO.FileDTO(2L, "Progress Photo.png", "image", "/api/download?fileName=ProgressPhoto.png"),
                new AppointmentDTO.FileDTO(3L, "Prescription.txt", "text", "/api/download?fileName=Prescription.txt")
        ));

        return appointmentDTO;
    }

    public boolean hasMedicAppointment(Long medicId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Medic medic = medicRepository.findById(medicId).orElseThrow();
        List<Appointment> appointments = appointmentRepository
                .findAppointmentByMedicIdAndStartDateTimeAndEndDateTime(medicId, startDateTime, endDateTime);
        return !appointments.isEmpty();
    }

    public Appointment createAppointment(CreateAppointmentDto dto) {
        var patient = patientRepository.findById(dto.getPatientId()).orElseThrow();
        if (patient == null) {
            throw new ServiceException("Patient not found");
        }
        var medic = medicRepository.findById(dto.getMedicId()).orElseThrow();
        if (medic == null) {
            throw new ServiceException("Medic not found");
        }
        var medicIntervals = medicIntervalRepository.findMedicIntervalByMedicIdAndStartDateTimeAndEndDateTime(
                medic.getId(), dto.getStartDateTime(), dto.getEndDateTime());

        if (medicIntervals.size() != 1) {
            throw new ServiceException("Medic does not have an interval in this time slot");
        }
        if (hasMedicAppointment(medic.getId(), dto.getStartDateTime(), dto.getEndDateTime())) {
            throw new ServiceException("Medic already has an appointment in this time slot");
        }

        List<AppointmentSymptom> symptoms = new ArrayList<>();
        dto.getSymptoms().forEach(symptom -> {
            symptoms.add(AppointmentSymptom.builder().symptom(symptom).build());
        });

        var appointment = Appointment.builder()
//                .medic(medic)
                .patient(patientRepository.findById(dto.getPatientId()).orElseThrow())
                .startDateTime(dto.getStartDateTime())
                .endDateTime(dto.getEndDateTime())
                .reason(dto.getReason())
                .symptoms(symptoms)
                .status("CREATED")
                .build();
        appointmentRepository.save(appointment);



        TreatmentPlan treatmentPlan = TreatmentPlan.builder()
                .parentAppointment(appointment)
                .description("")
                .medic(medic)
                .build()
                ;

//        appointment.setTreatmentPlan(treatmentPlan);
        appointmentRepository.save(appointment);
        treatmentPlanRepository.save(treatmentPlan);

        return appointment;
    }

    public List<Appointment> getPatientAppointments(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }


    public Appointment attachDiagnosticSymptomsToAppointment(AddSymptomsToAppointmentDto addSymptomsDto){
        var appointment = appointmentRepository.findById(addSymptomsDto.getAppointmentId()).orElseThrow();
        if (appointment == null) {
            throw new ServiceException("Appointment not found");
        }
        Diagnostic diagnostic = Diagnostic.builder().appointment(appointment)
//                .diagnosticDate(LocalDateTime.now())
                .build();
        appointment.setDiagnostic(diagnostic);

        List<Symptom> customSymptoms = symptomService.createSymptoms(addSymptomsDto.getCustomSymptoms());
        diagnostic.getSymptoms().addAll(customSymptoms);
        diagnostic.getSymptoms().addAll(symptomService.getSymptomsByIds(addSymptomsDto.getSymptomIds()));
        appointmentRepository.save(appointment);
        diagnosticRepository.save(diagnostic);
        return appointment;
    }

    public Appointment attachDiagnosticVerdictToAppointment(AttachDiseaseToDiagnosticDto dto){
        var appointment = appointmentRepository.findById(dto.getAppointmentId()).orElseThrow();
        if (appointment == null) {
            throw new ServiceException("Appointment not found");
        }
        var diagnostic = appointment.getDiagnostic();
        if (diagnostic == null) {
            throw new ServiceException("Diagnostic not found");
        }
        var disease = diseaseService.getDiseaseById(dto.getDiseaseId());
        diagnostic.setDisease(disease);
        diagnostic.setDescription(dto.getDescription());
        diagnostic.setStatus("VERDICTED");
        diagnosticRepository.save(diagnostic);
        return appointment;
    }

    public Appointment attachMedicationToAppointment(CreateMedicationDto createMedicationDto) {
        var appointment = appointmentRepository.findById(createMedicationDto.getAppointmentId()).orElseThrow();
        if (appointment == null) {
            throw new ServiceException("Appointment not found");
        }
        Medication medication = Medication.builder()
                .appointment(appointment)
                .startDateTime(createMedicationDto.getStartDateTime())
                .endDateTime(createMedicationDto.getEndDateTime())
                .recommendations(createMedicationDto.getRecommendations())
                .build();
        createMedicationDto.getMedicationDrugs().forEach(medicationDrugDto -> {
            MedicationDrug medicationDrug = MedicationDrug.builder()
                    .drug(drugRepository.findById(medicationDrugDto.getDrugId()).orElseThrow())
                    .dosage(medicationDrugDto.getDosage())
                    .frequency(medicationDrugDto.getFrequency())
                    .duration(medicationDrugDto.getDuration())
                    .instructions(medicationDrugDto.getInstructions())
                    .build();
            medication.getMedicationDrugs().add(medicationDrug);
        });
        appointment.setMedication(medication);
        appointmentRepository.save(appointment);
        medicationRepository.save(medication);
        return appointment;
    }

    public Appointment attachRecomandationToAppointment(CreateAppointmentRecomandationDto dto){
        var appointment = appointmentRepository.findById(dto.getAppointmentId()).orElseThrow();
        if (appointment == null) {
            throw new ServiceException("Appointment not found");
        }
        //concatenate a map to a string
        appointment.setRecommendation(dto.getRecommendations());
        appointment.setRecommendationMap(ConvertUtils.convertMapToString(dto.getRecommendationMap()));
        appointmentRepository.save(appointment);
        return appointment;
    }

}
