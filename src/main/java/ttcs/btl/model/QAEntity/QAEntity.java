package ttcs.btl.model.QAEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.model.comments.CommentsEntity;
import ttcs.btl.model.news.NewsEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "qas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class QAEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="content",columnDefinition = "text")
    private String content;

    @Column(name = "img",columnDefinition = "text")
    private String img;

    @Column(name="report")
    private Boolean report;

    @JsonManagedReference(value = "qa_likes")
    @OneToMany(mappedBy = "qaEntity", cascade = CascadeType.ALL)
    private  List<LikesQAEntity> likes;

    @JsonManagedReference(value = "qa_comment")
    @OneToMany(mappedBy = "qaEntity", cascade = CascadeType.ALL)
    private List<CommentsEntity> commentsEntities;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_qa_client_id", referencedColumnName = "id")
    private ClientEntity clientEntityQa;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
