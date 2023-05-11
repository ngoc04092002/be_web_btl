package ttcs.btl.service.qas;

import ttcs.btl.model.QAEntity.LikesQAEntity;
import ttcs.btl.model.QAEntity.QAEntity;

import java.util.List;

public interface IQAService {

    List<QAEntity> getAllQA();

    List<QAEntity> filterQA(String s,Integer limit, Integer offset);

    Boolean saveQA(QAEntity qaEntity);
    String deleteQA(Long id);

    List<LikesQAEntity> getLikes();

    List<LikesQAEntity> getLikesByQaId(LikesQAEntity likesQAEntity);

    Integer toggleLike(LikesQAEntity likesQAEntity);


}
