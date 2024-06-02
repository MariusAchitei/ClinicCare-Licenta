package ro.uaic.clinic_care.repository;

import ro.uaic.clinic_care.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
