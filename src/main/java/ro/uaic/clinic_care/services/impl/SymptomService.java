package ro.uaic.clinic_care.services.impl;

import ro.uaic.clinic_care.models.Symptom;
import ro.uaic.clinic_care.repository.SymptomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SymptomService {
    private final SymptomRepository symptomRepository;

    public List<Symptom> createSymptoms(Map<String, String> symptoms) {
        return symptomRepository.saveAll(symptoms.entrySet().stream()
                .map(symptom -> new Symptom(symptom.getKey(), symptom.getValue()))
                .collect(Collectors.toList()));
    }

    public List<Symptom> getSymptomsByIds(List<Long> symptomIds) {
        return symptomRepository.findAllById(symptomIds);
    }
}
