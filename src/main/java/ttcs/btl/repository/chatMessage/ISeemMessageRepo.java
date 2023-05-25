package ttcs.btl.repository.chatMessage;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.chatMessage.SeemModal;

import java.util.List;

@Repository
public interface ISeemMessageRepo extends MongoRepository<SeemModal,String> {

    SeemModal getByRid(String rid);

    @Aggregation(
            pipeline = {
                    "{$match: {$and:[{rid: {$in: ?0}},{isRep: {$eq: true}}]}}",
            }
    )
    List<SeemModal> findMessageRep(List<String> rids);
}
