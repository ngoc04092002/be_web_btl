package ttcs.btl.model.client;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ttcs.btl.dto.auth.UserResponse;
import ttcs.btl.model.QAEntity.QAEntity;
import ttcs.btl.model.bills.BillEntity;
import ttcs.btl.model.comments.CommentsEntity;
import ttcs.btl.model.news.NewsEntity;
import ttcs.btl.model.postRoom.PostRoomEntity;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="clients")
@Data
@RequiredArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ClientEntity(UserResponse userResponse){
        this.username=userResponse.username();
        this.email=userResponse.email();
        this.address=userResponse.address();
        this.gender=userResponse.gender();
        this.password=userResponse.password();
        this.sdt="";
        this.avatar="";
        this.role="user";
        this.createdAt=LocalDateTime.now();
    }

    @Column(name="username", nullable = false, columnDefinition = "nvarchar(255)")
    private String username;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="gender", nullable = false)
    private String gender;

    @Column(name="avatar", nullable = false, columnDefinition = "varchar(225) default ''")
    private String avatar;
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

    @OneToMany(mappedBy = "clientEntityComment", cascade = CascadeType.ALL)
    private List<CommentsEntity> commentsEntities;

    @OneToMany(mappedBy = "clientEntityBill", cascade = CascadeType.ALL)
    private List<BillEntity> billEntities;

    @OneToMany(mappedBy = "clientEntityPostRoom", cascade = CascadeType.ALL)
    private List<PostRoomEntity> postRoomEntities;
}
