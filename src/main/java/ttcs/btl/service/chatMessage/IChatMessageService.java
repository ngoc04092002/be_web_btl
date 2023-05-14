package ttcs.btl.service.chatMessage;

import ttcs.btl.dto.chatMessage.ChatMessageResponse;
import ttcs.btl.model.chatMessage.ChatMessageModal;

import java.util.List;

public interface IChatMessageService {
    ChatMessageModal saveMessage(ChatMessageModal chatMessageModal);

    List<ChatMessageResponse> getRidMessages(String rid);
}