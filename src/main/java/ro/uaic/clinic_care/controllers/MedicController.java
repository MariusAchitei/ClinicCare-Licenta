package ro.uaic.clinic_care.controllers;

import ro.uaic.clinic_care.dto.CreateMedicDto;
import ro.uaic.clinic_care.dto.medic_interval.CreateMedicIntervalDto;
import ro.uaic.clinic_care.models.Medic;
import ro.uaic.clinic_care.models.MedicInterval;
import ro.uaic.clinic_care.services.impl.MedicIntervalServiceImpl;
import ro.uaic.clinic_care.services.impl.MedicService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/medics")
public class MedicController {

    private final MedicService medicService;
    private final MedicIntervalServiceImpl medicIntervalService;

    @GetMapping
    public ResponseEntity<List<Medic>> getAllMedics() {
        List<Medic> medics = medicService.getAllMedics();
        return ResponseEntity.ok(medics);
    }

    @PostMapping
    public ResponseEntity<Medic> createMedic(@RequestBody CreateMedicDto dto) {
        Medic medic = medicService.createMedic(dto);
        return ResponseEntity.created(URI.create("/api/medics/" + medic.getId())).body(medic);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medic> getMedicById(@PathVariable Long medicId) {
        Medic medic = medicService.getMedicById(medicId);
        return ResponseEntity.ok(medic);
    }

    @GetMapping("/{id}/intervals")
    public ResponseEntity<List<MedicInterval>> getMedicIntervals(@PathVariable Long medicId,
                                                                 @RequestParam("datetime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                 LocalDateTime startDateTime,
                                                                 @RequestParam("datetime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                 LocalDateTime endDateTime) {
        List<MedicInterval> intervals = medicIntervalService.getMedicIntervals(medicId, startDateTime, endDateTime);
        return ResponseEntity.ok(intervals);
    }

    @PostMapping("/{id}/intervals")
    public ResponseEntity<MedicInterval> createMedicInterval(@PathVariable Long medicId, @RequestBody CreateMedicIntervalDto dto) {
        dto.setMedicId(medicId);
        MedicInterval interval = medicIntervalService.createMedicInterval(dto);
        return ResponseEntity.created(URI.create("/api/medics/" + medicId + "/intervals/" + interval.getId())).body(interval);
    }
}
