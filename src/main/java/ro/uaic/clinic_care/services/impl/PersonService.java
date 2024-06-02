package ro.uaic.clinic_care.services.impl;

import ro.uaic.clinic_care.dto.CreatePersonDto;
import ro.uaic.clinic_care.models.ContactInfo;
import ro.uaic.clinic_care.models.Person;
import ro.uaic.clinic_care.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person getPersonById(Long personId) {
        return personRepository.findById(personId).orElseThrow();
    }

    public Person createPerson(CreatePersonDto dto){
        Person person = Person.builder()
                .personalId(dto.getPersonalId())
                .lastName(dto.getLastName())
                .firstName(dto.getFirstName())
                .birthDate(dto.getBirthDate())
                .contactInfo(ContactInfo.builder()
                        .email(dto.getEmail())
                        .phone(dto.getPhone())
                        .address(dto.getAddress())
                        .city(dto.getCity())
                        .state(dto.getState())
                        .build())
                .build();
        return personRepository.save(person);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
}
