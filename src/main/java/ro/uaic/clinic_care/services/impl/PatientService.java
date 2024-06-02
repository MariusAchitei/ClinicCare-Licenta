package ro.uaic.clinic_care.services.impl;

import ro.uaic.clinic_care.dto.CreatePatientDto;
import ro.uaic.clinic_care.exceptions.ServiceException;
import ro.uaic.clinic_care.models.Patient;
import ro.uaic.clinic_care.models.Person;
import ro.uaic.clinic_care.repository.PatientRepository;
import ro.uaic.clinic_care.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    private final PersonRepository personRepository;

    public Patient getPatientById(Long patientId) {
        return patientRepository.findById(patientId).orElseThrow();
    }

    public Patient createPatient(CreatePatientDto dto) {
        Optional<Person> person = personRepository.findById(dto.getPersonId());
        if (person.isEmpty()) {
            throw new ServiceException("Person not found");
        }
        Patient patient = Patient.builder()
                .person(person.get())
                .build();
        return patientRepository.save(patient);
    }
}
