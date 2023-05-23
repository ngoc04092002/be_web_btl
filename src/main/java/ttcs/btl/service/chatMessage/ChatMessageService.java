package ttcs.btl.service.chatMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.dto.chatMessage.ChatMessageResponse;
import ttcs.btl.model.chatMessage.ChatMessageModal;
import ttcs.btl.repository.chatMessage.IChatMessageRepo;
import ttcs.btl.repository.clients.IClientRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService implements IChatMessageService{
    private final IChatMessageRepo iChatMessageRepo;
    private final IClientRepo iClientRepo;
    @Override
    public ChatMessageModal saveMessage(ChatMessageModal chatMessageModal) {
        return iChatMessageRepo.save(chatMessageModal);
    }

    @Override
    public List<ChatMessageModal> getRidMessages(String rid) {
        final var msgs = iChatMessageRepo.getByRid(rid);
        return msgs;
    }

    @Override
    public List<ChatMessageResponse> getAllUsersChatMessageTo(String to) {
        List<String> ids = iChatMessageRepo.findByUsersAndFromTo(to);
        List<ChatMessageResponse> chatMessageResponses = new ArrayList<>();
        for(String id:ids){
            final var user = iClientRepo.findById(Long.valueOf(id));
            if(user.isPresent()){
                ChatMessageResponse chatMessageResponse = new ChatMessageResponse(user.get());
                chatMessageResponses.add(chatMessageResponse);
            }
        }

        return chatMessageResponses;

    }

}
