package ttcs.btl.service.qas;

import ttcs.btl.dto.qas.QAUpdateRequest;
import ttcs.btl.model.QAEntity.LikesQAEntity;
import ttcs.btl.model.QAEntity.QAEntity;

import java.util.List;

public interface IQAService {

    List<QAEntity> getAllQA(Boolean report);

    List<QAEntity> getAllQAByUser(Long id);

    List<QAEntity> filterQA(String s,Integer limit, Integer offset);

    Boolean saveQA(QAEntity qaEntity);

    Boolean updateQA(QAUpdateRequest qaUpdateRequest);
    Boolean deleteQA(Long id);

    List<LikesQAEntity> getLikes();

    List<LikesQAEntity> getLikesByQaId(LikesQAEntity likesQAEntity);

    Integer toggleLike(LikesQAEntity likesQAEntity);


}
