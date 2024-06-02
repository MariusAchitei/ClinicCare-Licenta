package ro.uaic.clinic_care.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateMedicDto {

    private Long personId;

    private String title;

    private List<Long> specialtyIds = new ArrayList<>();
}
