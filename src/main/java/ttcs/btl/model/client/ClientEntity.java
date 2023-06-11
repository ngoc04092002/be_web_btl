package ttcs.btl.model.client;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import ttcs.btl.dto.auth.AuthRequestSocial;
import ttcs.btl.dto.auth.UserResponse;
import ttcs.btl.model.QAEntity.LikesQAEntity;
import ttcs.btl.model.QAEntity.QAEntity;
import ttcs.btl.model.bills.BillEntity;
import ttcs.btl.model.comments.CommentChild;
import ttcs.btl.model.comments.CommentsEntity;
import ttcs.btl.model.news.NewsEntity;
import ttcs.btl.model.postRoom.PostRoomEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "clients")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class ClientEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ClientEntity(UserResponse userResponse) {
        this.username = userResponse.username();
        this.email = userResponse.email();
        this.address = userResponse.address();
        this.gender = userResponse.gender();
        this.password = userResponse.password();
        this.sdt = "";
        this.avatar = "";
        this.role = "user";
        this.createdAt = LocalDateTime.now();
    }

    public ClientEntity(AuthRequestSocial authRequestSocial, String encodedPassword) {
        this.username = authRequestSocial.getUsername();
        this.email = authRequestSocial.getEmail();
        this.address = "";
        this.gender = "male";
        this.password = encodedPassword;
        this.sdt = "";
        this.avatar = authRequestSocial.getAvatar();
        this.role = "user";
        this.createdAt = LocalDateTime.now();
    }

    @Column(name = "username", nullable = false, columnDefinition = "nvarchar(255)")
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "avatar", nullable = false, columnDefinition = "text default ''")
    private String avatar;
    @Column(name = "sdt", nullable = false, columnDefinition = "varchar(225) default ''")
    private String sdt;

    @Column(name = "password")
    private String password;

    @Column(name = "role", nullable = false, columnDefinition = "varchar(225) default 'user'")
    private String role;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @JsonBackReference(value = "client_news")
    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL)
    private List<NewsEntity> newsEntityList;

    @JsonBackReference(value = "client_qa")
    @OneToMany(mappedBy = "clientEntityQa", cascade = CascadeType.ALL)
    private List<QAEntity> qaEntities;

    @JsonBackReference(value = "client_comment")
    @OneToMany(mappedBy = "clientComment", cascade = CascadeType.ALL)
    private List<CommentsEntity> commentsEntities;

    @JsonBackReference(value = "client_comment_child")
    @OneToMany(mappedBy = "clientComment", cascade = CascadeType.ALL)
    private List<CommentChild> commentChildren;

    @JsonBackReference(value = "client_bill")
    @OneToMany(mappedBy = "clientEntityBill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillEntity> billEntities;

    @JsonBackReference(value = "client_post_room")
    @OneToMany(mappedBy = "clientEntityPostRoom", cascade = CascadeType.ALL)
    private List<PostRoomEntity> postRoomEntities;


    @JsonBackReference(value = "like_client")
    @OneToMany(mappedBy = "clientLikeEntities", cascade = CascadeType.ALL)
    private List<LikesQAEntity> likesQAEntities;
}
