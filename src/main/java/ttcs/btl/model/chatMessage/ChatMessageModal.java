package ttcs.btl.model.chatMessage;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "chatMessages")
public class ChatMessageModal {
    @Id
    private String id;
    private String from;
    private String to;
    private String msg;
    private String rid;
    private String createdAt;

}
