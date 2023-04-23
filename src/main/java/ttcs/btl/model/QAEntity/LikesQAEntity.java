package ttcs.btl.model.QAEntity;

import jakarta.persistence.*;
import lombok.*;
import ttcs.btl.model.client.ClientEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "qas_likes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikesQAEntity {
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_qas_likes_id", referencedColumnName = "id")
    private QAEntity likesQAEntity;
}
