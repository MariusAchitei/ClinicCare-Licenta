package ro.uaic.clinic_care.models;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BaseEntity{
    private String personalId;
    private String lastName;
    private String firstName;
    private LocalDateTime birthDate;
    private ContactInfo contactInfo;
}
