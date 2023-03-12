package ttcs.btl.dto.auth;

import ttcs.btl.model.client.ClientEntity;

public record AuthResponse(Long id, String username, String email, String address, String gender, String avatar,
                           String sdt, String role, String token) {
    public AuthResponse(ClientEntity clientEntity, String token) {
        this(clientEntity.getId(), clientEntity.getUsername(), clientEntity.getEmail(), clientEntity.getAddress(),
             clientEntity.getGender(), clientEntity.getAvatar(), clientEntity.getSdt(), clientEntity.getRole(), token);
    }
}
