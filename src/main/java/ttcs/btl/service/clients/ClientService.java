package ttcs.btl.service.clients;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ttcs.btl.dto.clients.ClientResponse;
import ttcs.btl.dto.clients.PasswordRequest;
import ttcs.btl.dto.clients.UpdateClientRequest;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.model.client.EWaitingR;
import ttcs.btl.repository.clients.IClientRepo;
import ttcs.btl.repository.error.ArgumentException;
import ttcs.btl.repository.error.EditProfileException;

import java.util.List;
import java.util.Optional;

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


    @Override
    public ClientResponse updateClient(UpdateClientRequest updateClientRequest, String oldEmail) {
        Optional<ClientEntity> clientEntityOptional =
                Optional.ofNullable(iClientRepo.findByEmail(updateClientRequest.getEmail()));
        if(clientEntityOptional.isPresent() && !oldEmail.equals(clientEntityOptional.get().getEmail())){
            throw new ArgumentException("Email đã tồn tại!");
        }
        ClientEntity currentClientEntity = iClientRepo.findByEmail(oldEmail);
        String username = updateClientRequest.getUsername();
        String address = updateClientRequest.getAddress();
        String sdt = updateClientRequest.getSdt();
        if(StringUtils.isNotBlank(username)){
            currentClientEntity.setUsername(username);
        }
        if(StringUtils.isNotBlank(address)){
            currentClientEntity.setAddress(address);
        }
        if(StringUtils.isNotBlank(sdt)){
            currentClientEntity.setSdt(sdt);
        }
        currentClientEntity.setEmail(updateClientRequest.getEmail());
        currentClientEntity.setGender(updateClientRequest.getGender());
        currentClientEntity.setAvatar(updateClientRequest.getAvatar());
        iClientRepo.save(currentClientEntity);
        return new ClientResponse(currentClientEntity);
    }
}
