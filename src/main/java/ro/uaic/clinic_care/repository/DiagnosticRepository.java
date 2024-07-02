package ro.uaic.clinic_care.repository;

import ro.uaic.clinic_care.models.history.Diagnostic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {
}
