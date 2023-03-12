package ttcs.btl.model.postRooms;


import jakarta.persistence.*;
import lombok.Data;
import ttcs.btl.model.client.ClientEntity;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "post_room_info")
public class postRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_client_id", referencedColumnName = "id")
    private ClientEntity clientEntityPostRoomInfo;

    @Column(name = "imgOrVideo")
    private String imgOrVideo;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "price")
    private Long price;

    @Column(name = "room_type")
    private String room_type;

    @Column(name = "bed_room")
    private String bed_room;

    @Column(name = "sale")
    private Long sale;

    @Column(name = "acreage")
    private String acreage;

    @Column(name = "desc")
    private Long desc;

    @Column(name = "bathroom")
    private String bathroom;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
