package ttcs.btl.service.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.dto.clients.ClientResponse;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.repository.clients.IClientRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {

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
    public String deleteClient(Long id) {
        iClientRepo.deleteById(id);
        return "delete successfully";
    }
}
