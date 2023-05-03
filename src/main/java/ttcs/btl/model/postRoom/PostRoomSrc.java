package ttcs.btl.model.postRoom;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_room_src")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRoomSrc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "src", columnDefinition = "text")
    private String src;

    @JsonBackReference(value = "post_room_src")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_post_room_src_id", referencedColumnName = "id")
    private PostRoomEntity postRoomSrc;
}
