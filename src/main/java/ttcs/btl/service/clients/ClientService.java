package ttcs.btl.service.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ttcs.btl.dto.clients.ClientResponse;
import ttcs.btl.dto.clients.PasswordRequest;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.repository.clients.IClientRepo;
import ttcs.btl.repository.error.EditProfileException;
import ttcs.btl.repository.error.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {
    private final PasswordEncoder passwordEncoder;
    private final IClientRepo iClientRepo;

    @Override
    public List<ClientEntity> getAllCLients() {
        return iClientRepo.findAll();
    }

    @Override
    public ClientResponse saveClient(ClientEntity clientEntity) {
        ClientEntity clientEntity1 = iClientRepo.save(clientEntity);
        return new ClientResponse(clientEntity1);
    }

    @Override
    public String updatePassword(PasswordRequest passwordRequest) {
        final ClientEntity clientEntity = iClientRepo.findByEmail(passwordRequest.getEmail());
        if(clientEntity==null){
            throw new EditProfileException("");
        }
        boolean isMatcher = passwordEncoder.matches(passwordRequest.getOldPassword(), clientEntity.getPassword());
        if(!isMatcher){
            throw new EditProfileException("");
        }else{
            String newPassword = passwordRequest.getPassword();
            String encodedPassword = passwordEncoder.encode(newPassword);
            clientEntity.setPassword(encodedPassword);
            iClientRepo.save(clientEntity);
        }
        return "success";
    }


    @Override
    public String deleteClient(Long id) {
        iClientRepo.deleteById(id);
        return "delete successfully";
    }
}
