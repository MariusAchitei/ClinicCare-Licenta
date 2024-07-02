package ro.uaic.clinic_care.models.location;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountyRepository extends JpaRepository<County, Long> {

    Optional<County> findByName(String name);
}
