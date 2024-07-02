package ro.uaic.clinic_care.controllers.tst;

import ro.uaic.clinic_care.dto.CreateMedicDto;
import ro.uaic.clinic_care.dto.medic_interval.CreateMedicIntervalDto;
import ro.uaic.clinic_care.models.old.Medicc;
import ro.uaic.clinic_care.models.old.MedicInterval;
import ro.uaic.clinic_care.services.impl.MedicIntervalServiceImpl;
import ro.uaic.clinic_care.services.impl.MedicServiceImpl;
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

    private final MedicServiceImpl medicService;
    private final MedicIntervalServiceImpl medicIntervalService;

    @GetMapping
    public ResponseEntity<List<Medicc>> getAllMedics() {
        List<Medicc> medics = medicService.getAllMedics();
        return ResponseEntity.ok(medics);
    }

    @PostMapping
    public ResponseEntity<Medicc> createMedic(@RequestBody CreateMedicDto dto) {
        Medicc medic = medicService.createMedic(dto);
        return ResponseEntity.created(URI.create("/api/medics/" + medic.getId())).body(medic);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicc> getMedicById(@PathVariable Long medicId) {
        Medicc medic = medicService.getMedicById(medicId);
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
