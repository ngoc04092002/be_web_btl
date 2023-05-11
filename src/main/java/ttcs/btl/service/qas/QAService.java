package ttcs.btl.service.qas;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ttcs.btl.model.QAEntity.QAEntity;
import ttcs.btl.repository.qas.IQARepo;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QAService implements IQAService{

    private final IQARepo iqaRepo;
    @Override
    public List<QAEntity> getAllQA() {
        return iqaRepo.findAll();
    }

    @Override
    public List<QAEntity> filterQA(String s, Integer limit, Integer offset) {
        return iqaRepo.filterQA(s,limit,offset);
    }


    @Override
    public Boolean saveQA(QAEntity qaEntity) {
        try{

            iqaRepo.save(qaEntity);
            return true;
        }catch (Exception ex){
            System.out.println("QA===>"+ex.getMessage());
            return false;
        }
    }

    @Override
    public String deleteQA(Long id) {
        iqaRepo.deleteById(id);
        return "delete successfully";
    }
}
