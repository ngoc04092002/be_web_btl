package ttcs.btl.model.comments;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ttcs.btl.model.client.ClientEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment_child")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class CommentChild implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @JsonBackReference(value = "comment_child")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_comment_child_id", referencedColumnName = "id")
    private CommentsEntity commentsEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_client_comment_child_id", referencedColumnName = "id")
    private ClientEntity clientComment;
}
