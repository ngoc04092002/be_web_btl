package ttcs.btl.model.QAEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import ttcs.btl.model.client.ClientEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "qas_likes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class LikesQAEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long like_id;
    @Column(name = "client_id")
    private Long client_id;
    @Column(name = "post_id")
    private Long post_id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
