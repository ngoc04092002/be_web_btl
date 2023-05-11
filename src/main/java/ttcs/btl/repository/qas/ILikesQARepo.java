package ttcs.btl.repository.qas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.QAEntity.LikesQAEntity;
import ttcs.btl.model.QAEntity.QAEntity;
import ttcs.btl.model.client.ClientEntity;

import java.util.List;

@Repository
public interface ILikesQARepo extends JpaRepository<LikesQAEntity, Long> {

    void deleteByClientLikeEntitiesAndQaEntity(ClientEntity clientEntity, QAEntity qaEntity);

    List<LikesQAEntity> findAllByQaEntity(QAEntity qaEntity);
    LikesQAEntity findByClientLikeEntitiesAndQaEntity(ClientEntity clientEntity, QAEntity qaEntity);
}
