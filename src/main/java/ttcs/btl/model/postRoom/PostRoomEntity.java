package ttcs.btl.model.postRoom;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ttcs.btl.model.bills.BillEntity;
import ttcs.btl.model.client.ClientEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "post_rooms")
@Data
@RequiredArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class PostRoomEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "text", nullable = false)
    private String title;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "room_type", nullable = false)
    private String roomType;

    @Column(name = "bed_room", nullable = false)
    private String bedRoom;

    @Column(name = "sale", nullable = false)
    private String sale;

    @Column(name = "acreage", nullable = false)
    private String acreage;

    @Column(name = "limit_number_people", nullable = false)
    private Integer limitNumberPeople;

    @Column(name = "des", columnDefinition = "text", nullable = false)
    private String des;

    @Column(name = "bathroom", nullable = false)
    private String bathroom;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL)
    private BillEntity billEntity;
    @ManyToOne
    @JoinColumn(name = "fk_client_post_room_id", referencedColumnName = "id")
    private ClientEntity clientEntityPostRoom;

    @JsonManagedReference(value = "post_room_src")
    @OneToMany(mappedBy = "postRoomSrc", cascade = CascadeType.ALL)
    private List<PostRoomSrc> src;
}