package ttcs.btl.dto.clients;

import ttcs.btl.model.QAEntity.QAEntity;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.model.news.NewsEntity;

import java.util.List;


public record ClientResponse(Long id, String username, String email, String address, String gender, String sdt,
                             String role) {
    public ClientResponse(ClientEntity clientEntity) {
        this(clientEntity.getId(),clientEntity.getUsername(), clientEntity.getEmail(), clientEntity.getAddress(),
             clientEntity.getGender(), clientEntity.getSdt(), clientEntity.getRole());
    }
}