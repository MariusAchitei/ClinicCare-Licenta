package ro.uaic.clinic_care.models.history;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.uaic.clinic_care.models.BaseEntity;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drug extends BaseEntity {
    private String name;
    private String producer;
    private String description;
}

