package ttcs.btl.service.qas;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ttcs.btl.model.QAEntity.QAEntity;
import ttcs.btl.repository.qas.IQARepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QAService implements IQAService{

    private final IQARepo iqaRepo;
    @Override
    public List<QAEntity> getAllQA() {
        return iqaRepo.findAll();
    }

    @Override
    public QAEntity saveQA(QAEntity qaEntity) {
        return iqaRepo.save(qaEntity);
    }

    @Override
    public String deleteQA(Long id) {
        iqaRepo.deleteById(id);
        return "delete successfully";
    }
}
