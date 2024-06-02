package ro.uaic.clinic_care.dto.medic_interval;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Calendar;

@Data
public class CreateMedicIntervalDto {
    private Long medicId;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;
}
