package ttcs.btl.model.news;

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
    @Column(name = "body")
    private String body;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_news_entity_id", referencedColumnName = "id")
    private NewsEntity newsBody;
}
