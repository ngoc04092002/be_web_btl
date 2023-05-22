package ttcs.btl.repository.chatMessage;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.chatMessage.ChatMessageModal;

import java.util.List;

@Repository
public interface IChatMessageRepo extends MongoRepository<ChatMessageModal, String> {
    List<ChatMessageModal> getByRid(String rid);

    @Aggregation(
            pipeline = {
                    "{$match: {to: ?0}}"
            }
    )
    List<ChatMessageModal> findByUsersAndFromTo(String to);
}
