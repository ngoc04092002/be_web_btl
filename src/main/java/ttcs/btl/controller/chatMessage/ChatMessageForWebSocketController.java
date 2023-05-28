package ttcs.btl.controller.chatMessage;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ttcs.btl.model.chatMessage.ChatMessageModal;
import ttcs.btl.repository.error.ArgumentException;
import ttcs.btl.service.chatMessage.IChatMessageService;

@RestController
@RequiredArgsConstructor
public class ChatMessageForWebSocketController {

    private final IChatMessageService iChatMessageService;
    @MessageMapping("/message")
    @SendTo("/receive/message")
    public ChatMessageModal sendMessage(@RequestBody ChatMessageModal chatMessageModal) throws InterruptedException {
        if(StringUtils.isEmpty(chatMessageModal.getMsg())){
            throw new ArgumentException("Tin nhắn trống!");
        }
        Thread.sleep(1000);
        return iChatMessageService.saveMessage(chatMessageModal);
    }

    @MessageMapping("/message/delete")
    @SendTo("/receive/message/delete")
    public ChatMessageModal deleteChatMessage(@RequestBody ChatMessageModal chatMessageModal){
        return iChatMessageService.deleteChatMessage(chatMessageModal);
    }

}
