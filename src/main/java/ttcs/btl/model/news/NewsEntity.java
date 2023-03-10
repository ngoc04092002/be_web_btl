package ttcs.btl.model.news;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ttcs.btl.model.client.ClientEntity;

import java.time.LocalDateTime;


@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "img")
    private String img;

    @Column(name = "title")
    private String title;

    @Column(name = "des")
    private String des;

    @Column(name = "poster_name", nullable = false, columnDefinition = "nvarchar(255) default ''")
    private String posterName;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at",  nullable = false, updatable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_news_client_id", referencedColumnName = "id")
    private ClientEntity clientEntity;

}
