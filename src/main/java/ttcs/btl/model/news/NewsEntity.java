package ttcs.btl.model.news;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ttcs.btl.model.client.ClientEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class NewsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "img", columnDefinition = "text")
    private String img;

    @Column(name = "type", columnDefinition = "text")
    private String type;

    @Column(name = "topic", columnDefinition = "text")
    private String topic;

    @Column(name = "title", columnDefinition = "text")
    private String title;

    @Column(name = "des", columnDefinition = "text")
    private String des;

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

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_news_client_id", referencedColumnName = "id")
    private ClientEntity clientEntity;

    @JsonManagedReference(value = "fk_news_body")
    @OneToMany(mappedBy = "newsBody", cascade = CascadeType.ALL)
    private List<NewsPiece> newsBody;

}
