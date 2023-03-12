package ttcs.btl.model.bills;

import jakarta.persistence.*;
import lombok.Data;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.model.news.NewsEntity;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bill")
@Data
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_bill_client_id", referencedColumnName = "id")
    private ClientEntity clientEntityBill;

    @Column(name = "creat_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
