package ttcs.btl.repository.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.client.EWaitingR;

@Repository
public interface IEWRRepo extends JpaRepository<EWaitingR,Long> {

    EWaitingR findByEmail(String email);

    void deleteByEmail(String email);
}
