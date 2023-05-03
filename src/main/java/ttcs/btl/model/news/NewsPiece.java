package ttcs.btl.model.news;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "news_piece")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsPiece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long like_id;
    @Column(name = "title", columnDefinition = "text")
    private String title;
    @Column(name = "des", columnDefinition = "text")
    private String des;
    @Column(name = "img", columnDefinition = "text")
    private String img;
    @Column(name = "caption", columnDefinition = "text")
    private String caption;
    @Column(name = "body", columnDefinition = "text")
    private String body;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @JsonBackReference(value = "fk_news_body")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_news_entity_id", referencedColumnName = "id")
    private NewsEntity newsBody;
}
