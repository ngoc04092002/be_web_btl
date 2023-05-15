package ttcs.btl.model.feedbacks;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client_feedbacks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientFeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "content",columnDefinition = "text")
    private String content;

    @Column(name = "clientId")
    private Long clientId;
}
