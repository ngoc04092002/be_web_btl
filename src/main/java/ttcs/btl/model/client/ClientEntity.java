package ttcs.btl.model.client;

import jakarta.persistence.*;
import lombok.Data;
import ttcs.btl.model.QAEntity.QAEntity;
import ttcs.btl.model.comments.CommentsEntity;
import ttcs.btl.model.news.NewsEntity;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="clients")
@Data
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable = false, columnDefinition = "nvarchar(255)")
    private String username;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="gender", nullable = false)
    private String gender;

    @Column(name="sdt" , nullable = false, columnDefinition = "varchar(225) default ''")
    private String sdt;

    @Column(name="password")
    private String password;

    @Column(name="role", nullable = false, columnDefinition = "varchar(225) default 'user'")
    private String role;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL)
    private List<NewsEntity> newsEntityList;

    @OneToMany(mappedBy = "clientEntityQa", cascade = CascadeType.ALL)
    private List<QAEntity> qaEntities;

//    @OneToMany(mappedBy = "clientEntityComment", cascade = CascadeType.ALL)
//    private List<CommentsEntity> commentsEntities;
}
