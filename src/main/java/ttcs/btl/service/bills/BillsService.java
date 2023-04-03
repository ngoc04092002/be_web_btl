package ttcs.btl.service.bills;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.model.bills.BillEntity;
import ttcs.btl.model.bills.BillEntityRepository;
import ttcs.btl.repository.bills.IBillsRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillsService implements IBillsService{
    private final IBillsRepo iBillsRepo;
    @Override
    public List<BillEntity> getAllBills(){
        return iBillsRepo.findAll();
    }
    @Override
    public BillEntity saveBill(BillEntity billEntity){
        return iBillsRepo.save(billEntity);
    }
    @Override
    public String deleteBill(Long id){
        iBillsRepo.deleteById(id);
        return "Delete SuccessFully";
    }
}
