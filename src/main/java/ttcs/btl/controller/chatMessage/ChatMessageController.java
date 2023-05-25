package ttcs.btl.controller.chatMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.dto.chatMessage.ChatMessageResponse;
import ttcs.btl.model.chatMessage.ChatMessageModal;
import ttcs.btl.model.chatMessage.SeemModal;
import ttcs.btl.service.chatMessage.IChatMessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/")
public class ChatMessageController {

    private final IChatMessageService iChatMessageService;

    @GetMapping("get-msg")
    public List<ChatMessageModal> getRidMessages(@RequestParam String rid) {
        return iChatMessageService.getRidMessages(rid);
    }

    @GetMapping("get-users")
    public List<ChatMessageResponse> getAllUsersChatMessageTo(@RequestParam String to) {
        return iChatMessageService.getAllUsersChatMessageTo(to);
    }

    @GetMapping("check-miss-msg")
    public Boolean checkMissMessage(@RequestParam String userId) {
        return iChatMessageService.checkMissMessage(userId);
    }

    @GetMapping("get-status-room")
    public SeemModal getStatusRoom(@RequestParam String rid) {
        final var statusRoom = iChatMessageService.getStatusRoom(rid);
        if (statusRoom == null) {
            SeemModal seemModal = new SeemModal();
            seemModal.setRid(rid);
            seemModal.setIsRep(false);
            return seemModal;
        }
        return statusRoom;
    }

    @PostMapping("toggle-status")
    public SeemModal toggleStatusRoom(@RequestBody SeemModal seemModal) {
        return iChatMessageService.toggleStatusRoom(seemModal);
    }

}
