package ro.uaic.clinic_care.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.clinic_care.models.administrative.Clinic;
import ro.uaic.clinic_care.repository.ClinicRepository;

@Service
@RequiredArgsConstructor
public class ClinicServiceImpl {

    private final ClinicRepository clinicRepository;

    public void getClinicById(Long clinicId) {
        clinicRepository.findById(clinicId).orElseThrow();
    }

    public Clinic getClinicByName(String clinicName) {
        return clinicRepository.findByName(clinicName).orElseThrow();
    }

    public Clinic createClinic(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    public void deleteClinic(Long clinicId) {
        clinicRepository.deleteById(clinicId);
    }

    public Clinic updateClinic(Clinic clinic) {
        return clinicRepository.save(clinic);
    }




}
