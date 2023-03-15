package ttcs.btl.model.postRoom;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ttcs.btl.model.bills.BillEntity;
import ttcs.btl.model.client.ClientEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_rooms")
@Data
@RequiredArgsConstructor
public class PostRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "src")
    private String src;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "price", columnDefinition = "FLOAT(5,2)")
    private Double price;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "bed_room")
    private String bedRoom;

    @Column(name = "sale", columnDefinition = "FLOAT(5,2)")
    private Double sale;

    @Column(name = "length")
    private String length;

    @Column(name = "width")
    private String width;

    @Column(name = "des")
    private String des;

    @Column(name = "bathroom")
    private String bathroom;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_bill_id", referencedColumnName = "id")
    private BillEntity billEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_client_post_room_id", referencedColumnName = "id")
    private ClientEntity clientEntityPostRoom;
}