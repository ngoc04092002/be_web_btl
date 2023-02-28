package ttcs.btl.model.client;

import jakarta.persistence.*;
import lombok.Data;
import ttcs.btl.model.dailyPost.DailyPostEntity;

import java.util.Set;

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

    @Column(name="sdt")
    private String sdt;

    @Column(name="password")
    private String password;

    @Column(name="role", nullable = false, columnDefinition = "varchar(225) default 'user'")
    private String role;

    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL)
    private Set<DailyPostEntity> dailyPostEntitySet;
}
