package ttcs.btl.model.QAEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.model.comments.CommentsEntity;
import ttcs.btl.model.news.NewsEntity;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "qas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="content")
    private String content;

    @Column(name = "img")
    private String img;

    @ElementCollection
    @JoinTable(name = "qas_likes", joinColumns = @JoinColumn(name = "like_id"))
    @Column(name = "likes")
    List<LikesQAEntity> likes;

    @OneToMany(mappedBy = "qaEntity", cascade = CascadeType.ALL)
    private List<CommentsEntity> commentsEntities;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_qa_client_id", referencedColumnName = "id")
    private ClientEntity clientEntityQa;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
