package ttcs.btl.repository.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttcs.btl.dto.auth.AuthResponse;
import ttcs.btl.model.client.ClientEntity;

@Repository
public interface IClientRepo extends JpaRepository<ClientEntity, Long> {
    ClientEntity findByEmail(String email);


}