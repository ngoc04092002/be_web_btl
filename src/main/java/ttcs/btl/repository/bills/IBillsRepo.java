package ttcs.btl.repository.bills;

import org.springframework.data.jpa.repository.JpaRepository;
import ttcs.btl.model.bills.BillEntity;

public interface IBillsRepo extends JpaRepository<BillEntity, Long> {
}
