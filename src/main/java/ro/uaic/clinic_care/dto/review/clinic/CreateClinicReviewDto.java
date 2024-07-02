package ro.uaic.clinic_care.dto.review.clinic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateClinicReviewDto {

    private Long clinicId;
    private String content;
    private Integer rating;
    private Long patientId;
}
