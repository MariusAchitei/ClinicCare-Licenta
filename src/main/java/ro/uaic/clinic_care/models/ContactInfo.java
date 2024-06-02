package ro.uaic.clinic_care.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo {
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;

}
