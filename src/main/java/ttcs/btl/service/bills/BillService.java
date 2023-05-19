package ttcs.btl.service.bills;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.model.bills.BillEntity;
import ttcs.btl.repository.bills.IBillRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService implements IBillService {
    private final IBillRepo iBillRepo;


    @Override
    public List<BillEntity> getAllBills(Long id) {
        return iBillRepo.getAllByClientEntityBill_Id(id);
    }

    @Override
    public Boolean saveBill(BillEntity billEntity) {
        try{
            iBillRepo.save(billEntity);
            return true;
        }catch (Exception e){

            System.out.println("bill==>"+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteBill(Long id) {
        try{
            iBillRepo.deleteById(id);
            return true;
        }catch (Exception ex){
            System.out.println("delete bill====>"+ex.getMessage());
            ex.printStackTrace();
            return false;

        }
    }
}