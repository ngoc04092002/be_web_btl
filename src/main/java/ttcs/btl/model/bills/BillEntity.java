package ttcs.btl.model.bills;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import ttcs.btl.model.postRoom.PostRoomEntity;
import ttcs.btl.model.client.ClientEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "bills")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class BillEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "room_id")
    private Long rid;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "fk_bill_client_id", referencedColumnName = "id")
    private ClientEntity clientEntityBill;
}