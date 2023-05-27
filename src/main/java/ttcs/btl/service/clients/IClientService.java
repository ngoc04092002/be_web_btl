package ttcs.btl.service.clients;

import ttcs.btl.dto.clients.ClientResponse;
import ttcs.btl.dto.clients.PasswordRequest;
import ttcs.btl.dto.clients.UpdateClientRequest;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.model.news.NewsEntity;

import java.util.List;

public interface IClientService {

    List<ClientEntity> getAllClients();

    ClientResponse saveClient(ClientEntity clientEntity);

    String updatePassword(PasswordRequest passwordRequest);

    Boolean deleteClient(Long id);

    ClientResponse updateClient(UpdateClientRequest updateClientRequest, String oldEmail);
}
