package ro.uaic.clinic_care.dto.clinic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClinicListDto {

    private Long id;
    private String name;
    private String address;
    private String city;
    private String county;
    private Double latitude;
    private Double longitude;
    private String phone;
    private String email;
    private String website;
    private String description;
    private String mainImage;
    private List<String> options;
    private Double rating;
    private Integer reviews;
    private Integer medicCount;
}
