package ro.uaic.clinic_care.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreatePersonDto {

    private String personalId;
    private String firstName;
    private String lastName;
    private LocalDateTime birthDate;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;

}
