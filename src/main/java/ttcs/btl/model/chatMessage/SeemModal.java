package ttcs.btl.model.chatMessage;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "seemMessage")
public class SeemModal {
    @Id
    private String id;

    private String rid;
    private Boolean isRep;
}
