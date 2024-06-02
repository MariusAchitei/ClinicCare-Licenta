package ro.uaic.clinic_care.dto.medic_interval;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MedicIntervalFilterDto {
    List<Long> medicIds;
    List<Long> departmentIds;
    List<Long> clinicIds;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
}
