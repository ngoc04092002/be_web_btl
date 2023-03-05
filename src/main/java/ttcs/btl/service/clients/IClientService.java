package ttcs.btl.service.clients;

import ttcs.btl.dto.clients.ClientResponse;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.model.news.NewsEntity;

import java.util.List;

public interface IClientService {

    List<ClientEntity> getAllCLients();

    ClientResponse saveClient(ClientEntity clientEntity);

    String deleteClient(Long id);
}
