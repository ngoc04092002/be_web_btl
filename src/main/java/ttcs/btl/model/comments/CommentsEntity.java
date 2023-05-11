package ttcs.btl.model.comments;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ttcs.btl.model.QAEntity.QAEntity;
import ttcs.btl.model.client.ClientEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class CommentsEntity implements Serializable {
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

    @JsonBackReference(value = "qa_comment")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_qa-comment_id", referencedColumnName = "id")
    private QAEntity qaEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_client_comment_id", referencedColumnName = "id")
    private ClientEntity clientComment;
}
