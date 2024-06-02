package ro.uaic.clinic_care.repository;

import ro.uaic.clinic_care.models.Diagnostic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {
}
