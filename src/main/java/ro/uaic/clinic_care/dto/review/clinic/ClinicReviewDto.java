package ro.uaic.clinic_care.dto.review.clinic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClinicReviewDto {
    private Long id;
    private String content;
    private Integer rating;
    private String date;
    private String name;
    private boolean verified = true;
}
