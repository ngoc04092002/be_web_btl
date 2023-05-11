package ttcs.btl.model.QAEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import ttcs.btl.model.client.ClientEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "qas_likes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class LikesQAEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonManagedReference(value = "like_client")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_like_client_id", referencedColumnName = "id")
    private ClientEntity clientLikeEntities;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @JsonBackReference(value = "qa_likes")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_qa_likes_id", referencedColumnName = "id")
    private QAEntity qaEntity;

}
