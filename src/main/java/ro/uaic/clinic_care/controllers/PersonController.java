package ro.uaic.clinic_care.controllers;

import ro.uaic.clinic_care.dto.CreatePersonDto;
import ro.uaic.clinic_care.models.Person;
import ro.uaic.clinic_care.services.impl.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/persons")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long personId) {
        Person person = personService.getPersonById(personId);
        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody CreatePersonDto dto){
        return ResponseEntity.ok(personService.createPerson(dto));
    }

    @GetMapping
    ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return ResponseEntity.ok(persons);
    }

}
