package ttcs.btl.service.qas;

import ttcs.btl.model.QAEntity.QAEntity;

import java.util.List;

public interface IQAService {

    List<QAEntity> getAllQA();

    QAEntity saveQA(QAEntity qaEntity);
    String deleteQA(Long id);
}
