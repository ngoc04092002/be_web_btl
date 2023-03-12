package ttcs.btl.dto.clients;

import ttcs.btl.model.client.ClientEntity;


public record ClientResponse(Long id, String username, String email, String address, String gender, String avatar,
                             String sdt, String role) {
    public ClientResponse(ClientEntity clientEntity) {
        this(clientEntity.getId(), clientEntity.getUsername(), clientEntity.getEmail(), clientEntity.getAddress(),
             clientEntity.getGender(), clientEntity.getAvatar(), clientEntity.getSdt(), clientEntity.getRole());
    }
}