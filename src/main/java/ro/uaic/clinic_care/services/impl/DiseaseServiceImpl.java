package ro.uaic.clinic_care.services.impl;

import ro.uaic.clinic_care.dto.CreateDiseaseDto;
import ro.uaic.clinic_care.models.history.Disease;
import ro.uaic.clinic_care.repository.DiseaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiseaseServiceImpl {

    private final DiseaseRepository diseaseRepository;

    public Disease createDisease(CreateDiseaseDto diseaseDto) {
        return diseaseRepository.save(Disease.builder().name(diseaseDto.getName()).description(diseaseDto.getDescription()).build());
    }

    public Disease getDiseaseById(Long diseaseId) {
        return diseaseRepository.findById(diseaseId).orElseThrow();
    }


}
