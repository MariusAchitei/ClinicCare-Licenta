package ro.uaic.clinic_care.dto.clinic;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CreateClinicDTO {
    private String name;
    private String address;
    private String description;
//    private ContactDTO contact;
    private String phone;
    private String email;
    private String website;
    private CoordinatesDTO coordinates;
    private double lng;
    private double lat;
    private String county;
    private String city;
//    private List<String> specialties;
//    private List<String> services;

    private String specialties;
    private String services;


//    private String specialties;
//    private List<String> services;
//    private MultipartFile[] files;

    @Data
    public static class ContactDTO {
        private String phone;
        private String email;
        private String website;
    }

    @Data
    public static class CoordinatesDTO {
        private double lat;
        private double lng;
    }
}
