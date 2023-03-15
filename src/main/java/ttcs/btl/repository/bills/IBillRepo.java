package ttcs.btl.repository.bills;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.bills.BillEntity;

@Repository
public interface IBillRepo extends JpaRepository<BillEntity, Long> {
}