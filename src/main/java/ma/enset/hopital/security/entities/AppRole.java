package ma.enset.hopital.security.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class AppRole {
    @Id
    private String role;

}
