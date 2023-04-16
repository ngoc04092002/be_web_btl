package ttcs.btl.service.bills;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ttcs.btl.model.bills.BillEntity;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.repository.bills.IBillRepo;
import ttcs.btl.repository.clients.IClientRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillService implements IBillService {
    private final IBillRepo iBillRepo;

    private final IClientRepo iClientRepo;

    @Override
    public List<BillEntity> getAllBills() {
        return iBillRepo.findAll();
    }

    @Override
    public Boolean saveBill(BillEntity billEntity) {
        Optional<ClientEntity> clientEntityOptional = iClientRepo.findById(billEntity.getClientEntityBill()
                                                                                   .getId());
        if (clientEntityOptional.isPresent()) {
            ClientEntity clientEntity = clientEntityOptional.get();
            billEntity.setClientEntityBill(clientEntity);
            iBillRepo.save(billEntity);
            return true;
        }
        return false;
    }
}