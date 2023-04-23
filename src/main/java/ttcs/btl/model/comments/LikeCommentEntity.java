package ttcs.btl.model.comments;

import jakarta.persistence.*;
import lombok.*;
import ttcs.btl.model.QAEntity.QAEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment_likes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long like_id;
    @Column(name = "client_id")
    private Long client_id;
    @Column(name = "post_id")
    private Long post_id;
    @Column(name = "comment_id")
    private Long comment_id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_comment_likes_id", referencedColumnName = "id")
    private CommentsEntity commentsEntity;
}
