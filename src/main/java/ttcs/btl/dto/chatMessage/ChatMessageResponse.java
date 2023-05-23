package ttcs.btl.dto.chatMessage;

import ttcs.btl.model.client.ClientEntity;

public record ChatMessageResponse(Long id, String img, String username) {
    public ChatMessageResponse(ClientEntity clientEntity) {
        this(clientEntity.getId(), clientEntity.getAvatar(), clientEntity.getUsername());
    }
}
