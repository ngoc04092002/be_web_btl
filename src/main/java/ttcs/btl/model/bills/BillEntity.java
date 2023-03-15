package ttcs.btl.model.bills;

import jakarta.persistence.*;
import lombok.Data;
import ttcs.btl.model.postRoom.PostRoomEntity;
import ttcs.btl.model.client.ClientEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "bills")
@Data
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @OneToOne(mappedBy = "billEntity")
    private PostRoomEntity postRoomEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_bill_client_id", referencedColumnName = "id")
    private ClientEntity clientEntityBill;
}