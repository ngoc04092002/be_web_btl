package ttcs.btl.service.bills;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.model.bills.BillEntity;
import ttcs.btl.repository.bills.IBillRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService implements IBillService{
    private final IBillRepo iBillRepo;

    @Override
    public List<BillEntity> getAllBills(){
        return iBillRepo.findAll();
    }
    @Override
    public BillEntity saveBill(BillEntity billEntity){
        return iBillRepo.save(billEntity);
    }
}