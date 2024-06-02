package ro.uaic.clinic_care.repository;

import ro.uaic.clinic_care.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
