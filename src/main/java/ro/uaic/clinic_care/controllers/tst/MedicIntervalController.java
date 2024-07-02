package ro.uaic.clinic_care.controllers.tst;

import ro.uaic.clinic_care.dto.medic_interval.MedicIntervalFilterDto;
import ro.uaic.clinic_care.models.old.MedicInterval;
import ro.uaic.clinic_care.services.impl.MedicIntervalServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/medic-interval")
@RequiredArgsConstructor
public class MedicIntervalController {

    private final MedicIntervalServiceImpl medicIntervalService;

    @GetMapping("/{id}")
    public ResponseEntity<MedicInterval> getMedicIntervalById(@PathVariable Long medicIntervalId) {
        MedicInterval medicInterval = medicIntervalService.getMedicIntervalById(medicIntervalId);
        return ResponseEntity.ok(medicInterval);
    }

    @GetMapping
    public ResponseEntity<List<MedicInterval>> getMedicIntervalsByFilter(@RequestBody(required = false) MedicIntervalFilterDto filter) {
        List<MedicInterval> medicIntervals = medicIntervalService.getMedicIntervalsByFilter(filter);
        return ResponseEntity.ok(medicIntervals);
    }

//    @PostMapping
//    public ResponseEntity<MedicInterval> createMedicInterval(@RequestBody CreateMedicIntervalDto dto){
//        MedicInterval medicInterval =medicIntervalService.createMedicInterval(dto);
//        return ResponseEntity.ok(medicInterval);
//    }

}
