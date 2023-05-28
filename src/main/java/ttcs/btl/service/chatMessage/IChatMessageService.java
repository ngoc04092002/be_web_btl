package ttcs.btl.service.chatMessage;

import ttcs.btl.dto.chatMessage.ChatMessageResponse;
import ttcs.btl.model.QAEntity.QAEntity;
import ttcs.btl.model.chatMessage.ChatMessageModal;
import ttcs.btl.model.chatMessage.SeemModal;

import java.util.List;

public interface IChatMessageService {
    ChatMessageModal saveMessage(ChatMessageModal chatMessageModal);

    List<ChatMessageModal> getRidMessages(String rid);

    List<ChatMessageResponse> getAllUsersChatMessageTo(String to);

    SeemModal getStatusRoom(String rid);

    SeemModal toggleStatusRoom(SeemModal seemModal);

    Boolean checkMissMessage(String userId);

    ChatMessageModal deleteChatMessage(ChatMessageModal chatMessageModal);

}
