package ro.uaic.clinic_care.controllers;

import ro.uaic.clinic_care.dto.CreatePatientDto;
import ro.uaic.clinic_care.models.Patient;
import ro.uaic.clinic_care.services.impl.AppointmentServiceImpl;
import ro.uaic.clinic_care.services.impl.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final AppointmentServiceImpl appointmentService;

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody CreatePatientDto dto) {
        Patient patient = patientService.createPatient(dto);
        return ResponseEntity.created(URI.create("/api/patients/" + patient.getId())).body(patient);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long patientId) {
        Patient patient = patientService.getPatientById(patientId);
        return ResponseEntity.ok(patient);
    }

}

