package ro.uaic.clinic_care.models.old;

import lombok.*;
import ro.uaic.clinic_care.models.BaseEntity;
import ro.uaic.clinic_care.models.ContactInfo;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BaseEntity {
    private String personalId;
    private String lastName;
    private String firstName;
    private LocalDateTime birthDate;
    private ContactInfo contactInfo;
}
