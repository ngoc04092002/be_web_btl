package ttcs.btl.service.auth;

import ttcs.btl.dto.auth.AuthResponse;
import ttcs.btl.dto.auth.UserResponse;
import ttcs.btl.dto.clients.ClientResponse;
import ttcs.btl.model.client.ClientEntity;

public interface IAuthService {

    ClientResponse saveUser(UserResponse userResponse);

    ClientEntity fetchUser(String email);

}
