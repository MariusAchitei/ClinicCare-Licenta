package ro.uaic.clinic_care.models;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drug extends BaseEntity{
    private String name;
    private String producer;
    private String description;
}

