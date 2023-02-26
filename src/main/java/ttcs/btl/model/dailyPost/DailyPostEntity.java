package ttcs.btl.model.dailyPost;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="dailyPosts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "img")
    private String img;

    @Column(name="title")
    private String title;

    @Column(name="des")
    private String des;

    @Column(name="poster_name", nullable = false)
    private String posterName;

    @OneToMany(mappedBy = "dailyPostEntity")
    private Set<Likes> likes;

    @OneToMany(mappedBy = "dailyPostEntity")
    private Set<Favorites> favorites;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;
}
