package ro.uaic.clinic_care.dto;

import lombok.Data;

import java.util.Calendar;

@Data
public class TimeSlotDto {
    Calendar startDateTime;
    Calendar endDateTime;
}
