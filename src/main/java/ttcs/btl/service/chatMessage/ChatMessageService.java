package ttcs.btl.service.chatMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.dto.chatMessage.ChatMessageResponse;
import ttcs.btl.dto.clients.ClientResponse;
import ttcs.btl.model.chatMessage.ChatMessageModal;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.repository.chatMessage.IChatMessageRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService implements IChatMessageService{
    private final IChatMessageRepo iChatMessageRepo;
    @Override
    public ChatMessageModal saveMessage(ChatMessageModal chatMessageModal) {
        return iChatMessageRepo.save(chatMessageModal);
    }

    @Override
    public List<ChatMessageResponse> getRidMessages(String rid) {
        final var msgs = iChatMessageRepo.getByRid(rid);
        return msgs.stream().map(this::chatMessageResponse).toList();
    }

    private ChatMessageResponse chatMessageResponse(ChatMessageModal chatMessageModal) {
        return new ChatMessageResponse(chatMessageModal);
    }
}
