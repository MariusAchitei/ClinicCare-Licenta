package ro.uaic.clinic_care.services.impl;

import ro.uaic.clinic_care.dto.CreateMedicDto;
import ro.uaic.clinic_care.exceptions.ServiceException;
import ro.uaic.clinic_care.models.Department;
import ro.uaic.clinic_care.models.Medic;
import ro.uaic.clinic_care.models.MedicDepartment;
import ro.uaic.clinic_care.models.Person;
import ro.uaic.clinic_care.repository.DepartmentRepository;
import ro.uaic.clinic_care.repository.MedicRepository;
import ro.uaic.clinic_care.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicService {
    private final MedicRepository medicRepository;

    private final PersonRepository personRepository;

    private final DepartmentRepository departmentRepository;

   public Medic getMedicById(Long medicId) {
        return medicRepository.findById(medicId).orElseThrow();
    }

    public List<Medic> getAllMedics() {
        return medicRepository.findAll();
    }

    public Medic createMedic(CreateMedicDto dto) {
        Optional<Person> medicPerson = personRepository.findById(dto.getPersonId());
        if(medicPerson.isEmpty()) {
            throw new ServiceException("Person not found");
        }

        List<Department> departments = departmentRepository.findAllById(dto.getSpecialtyIds());

        Medic medic = Medic.builder()
                .person(medicPerson.get())
                .departments(departments.stream()
                        .map(department -> MedicDepartment.builder().department(department).build()).toList())
                .title(dto.getTitle())
                .build();
        return medicRepository.save(medic);
    }

}
