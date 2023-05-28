package ttcs.btl.service.chatMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.dto.chatMessage.ChatMessageResponse;
import ttcs.btl.model.chatMessage.ChatMessageModal;
import ttcs.btl.model.chatMessage.SeemModal;
import ttcs.btl.repository.chatMessage.IChatMessageRepo;
import ttcs.btl.repository.chatMessage.ISeemMessageRepo;
import ttcs.btl.repository.clients.IClientRepo;
import ttcs.btl.repository.error.ArgumentException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService implements IChatMessageService {
    private final IChatMessageRepo iChatMessageRepo;
    private final IClientRepo iClientRepo;
    private final ISeemMessageRepo iSeemMessageRepo;


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

        for (String id : ids) {
            final var user = iClientRepo.findById(Long.valueOf(id));
            if (user.isPresent()) {
                ChatMessageResponse chatMessageResponse = new ChatMessageResponse(user.get());
                chatMessageResponses.add(chatMessageResponse);
            }
        }

        return chatMessageResponses;

    }

    @Override
    public SeemModal getStatusRoom(String rid) {
        return iSeemMessageRepo.getByRid(rid);
    }

    @Override
    public SeemModal toggleStatusRoom(SeemModal seemModal) {
        final var room = iSeemMessageRepo.getByRid(seemModal.getRid());
        if (room == null) {
            return iSeemMessageRepo.save(seemModal);
        } else {
            room.setIsRep(seemModal.getIsRep());
            iSeemMessageRepo.save(room);
            return room;
        }
    }

    @Override
    public Boolean checkMissMessage(String userId) {
        final var rids = iChatMessageRepo.getRids(userId);
        final var userNotRep = iSeemMessageRepo.findMessageRep(rids);
        if (userNotRep == null || userNotRep.size() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public ChatMessageModal deleteChatMessage(ChatMessageModal chatMessageModal) {
        try {
            iChatMessageRepo.deleteById(chatMessageModal.getId());
            return chatMessageModal;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new ArgumentException("");
        }

    }

}
