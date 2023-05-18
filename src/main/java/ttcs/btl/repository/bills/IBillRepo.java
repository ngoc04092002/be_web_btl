package ttcs.btl.repository.bills;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.bills.BillEntity;
import ttcs.btl.model.client.ClientEntity;

import java.util.List;

@Repository
public interface IBillRepo extends JpaRepository<BillEntity, Long> {

    void deleteById(Long id);

    List<BillEntity> getAllByClientEntityBill_Id(Long id);
}