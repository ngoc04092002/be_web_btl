package ttcs.btl.service.bills;

import org.springframework.http.ResponseEntity;
import ttcs.btl.model.bills.BillEntity;

import java.util.List;

public interface IBillService {
    List<BillEntity> getAllBills(Long id);
    Boolean saveBill(BillEntity billEntity);

}