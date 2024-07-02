package ro.uaic.clinic_care.models;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Embeddable
public class Person {
    private String personalId;
    private String lastName;
    private String firstName;
    private LocalDateTime birthDate;
    private ContactInfo contactInfo;
}
