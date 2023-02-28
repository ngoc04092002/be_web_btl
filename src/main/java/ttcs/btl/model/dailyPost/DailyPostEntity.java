package ttcs.btl.model.dailyPost;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ttcs.btl.model.client.ClientEntity;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "daily_posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyPostEntity {

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

    @ManyToOne
    @JoinColumn(name = "client_entity_id")
    private ClientEntity clientEntity;
    @Column(name = "likes")
    @ElementCollection
    @JoinTable(name = "daily_post_entity_likes", joinColumns = @JoinColumn(name = "daily_post_entity_id"))
    List<String> likes;

    @ElementCollection
    @Column(name = "favorites")
    @JoinTable(name = "daily_post_entity_favorites", joinColumns = @JoinColumn(name = "daily_post_entity_id"))
    List<String> favorites;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP default now()")
    private Date createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP default now()")
    private Date updatedAt;
}
