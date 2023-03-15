package ttcs.btl.service.bills;

import ttcs.btl.model.bills.BillEntity;

import java.util.List;

public interface IBillService {
    List<BillEntity> getAllBills();
    BillEntity saveBill(BillEntity billEntity);

}