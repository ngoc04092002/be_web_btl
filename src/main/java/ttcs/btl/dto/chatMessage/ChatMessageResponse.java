package ttcs.btl.dto.chatMessage;

import ttcs.btl.model.chatMessage.ChatMessageModal;

public record ChatMessageResponse(String from, String to, String msg) {
    public ChatMessageResponse(ChatMessageModal chatMessageModal) {
        this(chatMessageModal.getFrom(), chatMessageModal.getTo(), chatMessageModal.getMsg());
    }
}
