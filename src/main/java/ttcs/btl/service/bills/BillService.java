package ttcs.btl.service.bills;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ttcs.btl.model.bills.BillEntity;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.model.postRoom.PostRoomEntity;
import ttcs.btl.repository.bills.IBillRepo;
import ttcs.btl.repository.clients.IClientRepo;
import ttcs.btl.repository.postRoom.IPostRoomRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillService implements IBillService {
    private final IBillRepo iBillRepo;


    private final IPostRoomRepo iPostRoomRepo;

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
}