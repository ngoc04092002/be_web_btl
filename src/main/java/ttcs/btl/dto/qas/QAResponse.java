package ttcs.btl.dto.qas;

import ttcs.btl.model.QAEntity.LikesQAEntity;
import ttcs.btl.model.QAEntity.QAEntity;
import ttcs.btl.model.client.ClientEntity;

import java.time.LocalDateTime;
import java.util.List;

public record QAResponse(Long id, String img, String content, List<LikesQAEntity> likes, LocalDateTime createdAt,
                         Long fk_qa_client_id) {
    public QAResponse(QAEntity qaEntity) {
        this(qaEntity.getId(), qaEntity.getImg(), qaEntity.getContent(), qaEntity.getLikes(), qaEntity.getCreatedAt()
                , qaEntity.getClientEntityQa().getId());
    }
}
