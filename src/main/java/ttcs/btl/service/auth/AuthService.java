package ttcs.btl.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.dto.auth.UserResponse;
import ttcs.btl.dto.clients.ClientResponse;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.repository.clients.IClientRepo;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final IClientRepo iClientRepo;

    @Override
    public ClientResponse saveUser(UserResponse userResponse) {
        ClientEntity clientEntity=new ClientEntity(userResponse);
        ClientEntity clientEntity1 = iClientRepo.save(clientEntity);
        return new ClientResponse(clientEntity1);
    }

    @Override
    public ClientEntity fetchUser(String email) {
        return iClientRepo.findByEmail(email);
    }


}
