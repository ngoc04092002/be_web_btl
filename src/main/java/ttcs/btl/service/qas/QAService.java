package ttcs.btl.service.qas;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.dto.qas.QAUpdateRequest;
import ttcs.btl.model.QAEntity.LikesQAEntity;
import ttcs.btl.model.QAEntity.QAEntity;
import ttcs.btl.repository.qas.ILikesQARepo;
import ttcs.btl.repository.qas.IQARepo;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QAService implements IQAService {

    private final IQARepo iqaRepo;
    private final ILikesQARepo iLikesQARepo;

    @Override
    public List<QAEntity> getAllQA(Boolean report) {
        return iqaRepo.getAllByReport(report);
    }

    @Override
    public List<QAEntity> getAllQAByUser(Long id) {
        return iqaRepo.getAllByClientEntityQa_Id(id);
    }

    @Override
    public List<QAEntity> filterQA(String s, Integer limit, Integer offset) {
        return iqaRepo.filterQA(s, limit, offset);
    }


    @Override
    public Boolean saveQA(QAEntity qaEntity) {
        try {

            iqaRepo.save(qaEntity);
            return true;
        } catch (Exception ex) {
            System.out.println("QA===>" + ex.getMessage());
            return false;
        }
    }

    @Override
    public Boolean updateQA(QAUpdateRequest qaUpdateRequest) {
        try {
            final var qa = iqaRepo.getById(qaUpdateRequest.getId());
            qa.setReport(qaUpdateRequest.getReport());
            return true;
        } catch (Exception e) {
            System.out.println("update qa====>" + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Boolean deleteQA(Long id) {
        try {
            iqaRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println("deleta qa===>" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<LikesQAEntity> getLikes() {
        return iLikesQARepo.findAll();
    }

    @Override
    public List<LikesQAEntity> getLikesByQaId(LikesQAEntity likesQAEntity) {
        return iLikesQARepo.findAllByQaEntity(likesQAEntity.getQaEntity());
    }

    @Override
    public Integer toggleLike(LikesQAEntity likesQAEntity) {
        final var amountLike = getLikesByQaId(likesQAEntity).size();
        System.out.println(likesQAEntity.getClientLikeEntities()
                                   .getId() + "-" + likesQAEntity.getQaEntity()
                .getId());
        final var currentLike = iLikesQARepo.findByClientLikeEntitiesAndQaEntity(likesQAEntity.getClientLikeEntities(),
                                                                                 likesQAEntity.getQaEntity());
        if (currentLike == null) {
            iLikesQARepo.save(likesQAEntity);
            return amountLike + 1;
        }
        iLikesQARepo.deleteByClientLikeEntitiesAndQaEntity(likesQAEntity.getClientLikeEntities(),
                                                           likesQAEntity.getQaEntity());
        return amountLike - 1;
    }
}
