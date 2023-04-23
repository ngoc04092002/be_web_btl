package ttcs.btl.model.comments;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ttcs.btl.model.QAEntity.QAEntity;
import ttcs.btl.model.client.ClientEntity;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "commentsEntity", cascade = CascadeType.ALL)
    private List<LikeCommentEntity> likes;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_comments_id", referencedColumnName = "id")
    private QAEntity qaEntityComment;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_client_comment_id", referencedColumnName = "id")
    private ClientEntity clientEntityComment;
}
