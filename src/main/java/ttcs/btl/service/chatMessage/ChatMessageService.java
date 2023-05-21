package ttcs.btl.service.chatMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.dto.chatMessage.ChatMessageResponse;
import ttcs.btl.model.chatMessage.ChatMessageModal;
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
    public List<ChatMessageModal> getRidMessages(String rid) {
        final var msgs = iChatMessageRepo.getByRid(rid);
        return msgs;
    }

    private ChatMessageResponse chatMessageResponse(ChatMessageModal chatMessageModal) {
        return new ChatMessageResponse(chatMessageModal);
    }
}
