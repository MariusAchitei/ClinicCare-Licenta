package ro.uaic.clinic_care.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.uaic.clinic_care.dto.clinic.ClinicDto;
import ro.uaic.clinic_care.dto.clinic.ClinicListDto;
import ro.uaic.clinic_care.dto.clinic.CreateClinicDTO;
import ro.uaic.clinic_care.services.ClinicService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clinics")
public class ClinicsController {

    private final ClinicService clinicService;

    @PostMapping
    public ResponseEntity<String> saveClinic(@RequestParam(name="mainPhoto") MultipartFile mainPhoto,
            @RequestParam(name = "files") MultipartFile[] files, @ModelAttribute CreateClinicDTO clinicDTO) {
        clinicService.saveClinic(clinicDTO, mainPhoto, files);
        return new ResponseEntity<>("Clinic saved successfully", HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<ClinicListDto>> getAllClinics() {
        List<ClinicListDto> clinics = clinicService.getAllClinics();
        return ResponseEntity.ok(clinics);
    }

    @PutMapping("/{clinicId}")
    public ResponseEntity<String> updateClinic(@RequestParam(name="mainPhoto") MultipartFile mainPhoto,
                                               @RequestParam(name = "files") MultipartFile[] files,
                                               @PathVariable Long clinicId,
                                                @ModelAttribute CreateClinicDTO clinicDTO) {
        clinicService.updateClinic(clinicId, mainPhoto, files,clinicDTO);
        return new ResponseEntity<>("Clinic updated successfully", HttpStatus.OK);
    }

    @GetMapping("/{clinicId}")
    public ResponseEntity<ClinicDto> getClinicById(@PathVariable Long clinicId) {
        ClinicDto clinic = clinicService.getClinicById(clinicId);
        return ResponseEntity.ok(clinic);
    }
}
