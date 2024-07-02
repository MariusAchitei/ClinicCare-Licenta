package ro.uaic.clinic_care.controllers.tst;

import ro.uaic.clinic_care.dto.appointment.AddSymptomsToAppointmentDto;
import ro.uaic.clinic_care.dto.appointment.AttachDiseaseToDiagnosticDto;
import ro.uaic.clinic_care.dto.appointment.CreateAppointmentDto;
import ro.uaic.clinic_care.dto.appointment.CreateMedicationDto;
import ro.uaic.clinic_care.models.history.Appointment;
import ro.uaic.clinic_care.services.impl.AppointmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/appointments")
public class AppointmentController {

    private final AppointmentServiceImpl appointmentService;

    @GetMapping
    public ResponseEntity<List<Appointment>> getPatientAppointments(@RequestParam(required = false) Long patientId) {
        List<Appointment> appointments = appointmentService.getPatientAppointments(patientId);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody CreateAppointmentDto dto) {
        Appointment appointment = appointmentService.createAppointment(dto);
        return ResponseEntity.created(URI.create("/api/patients/" + dto.getPatientId() + "/appointments/" + appointment.getId())).body(appointment);
    }
    
    @PostMapping("/{appointmentId}/symptoms")
    public ResponseEntity<Appointment> addSymptoms(@PathVariable Long appointmentId,
                                                   @RequestBody AddSymptomsToAppointmentDto dto) {
        Appointment appointment = appointmentService.attachDiagnosticSymptomsToAppointment(dto);
        return ResponseEntity.ok(appointment);
    }
    
    @PostMapping("/{appointmentId}/verdict")
    public ResponseEntity<Appointment> addVerdict(@PathVariable Long appointmentId,
                                                  @RequestBody AttachDiseaseToDiagnosticDto dto) {
        Appointment appointment = appointmentService.attachDiagnosticVerdictToAppointment(dto);
        return ResponseEntity.ok(appointment);
    }
    
    @PostMapping("/{appointmentId}/medication")
    public ResponseEntity<Appointment> addMedication(@PathVariable Long appointmentId,
                                                     @RequestBody CreateMedicationDto dto) {
        Appointment appointment = appointmentService.attachMedicationToAppointment(dto);
        return ResponseEntity.ok(appointment);
    }

}
