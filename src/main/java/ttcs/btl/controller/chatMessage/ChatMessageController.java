package ttcs.btl.controller.chatMessage;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.dto.chatMessage.ChatMessageResponse;
import ttcs.btl.model.QAEntity.QAEntity;
import ttcs.btl.model.chatMessage.ChatMessageModal;
import ttcs.btl.repository.error.ArgumentException;
import ttcs.btl.service.chatMessage.IChatMessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/")
public class ChatMessageController {

    private final IChatMessageService iChatMessageService;

    @GetMapping("get-msg")
    public List<ChatMessageModal> getRidMessages(@RequestParam String rid){
        return iChatMessageService.getRidMessages(rid);
    }

}
