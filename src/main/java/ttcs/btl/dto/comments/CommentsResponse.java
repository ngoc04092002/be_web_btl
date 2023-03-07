package ttcs.btl.dto.comments;

import ttcs.btl.model.comments.CommentsEntity;

import java.time.LocalDateTime;

public record CommentsResponse(Long id, String content, LocalDateTime createdAt, Long fk_comments_id,
                               Long fk_client_comment_id) {
    public CommentsResponse(CommentsEntity commentsEntity) {
        this(commentsEntity.getId(), commentsEntity.getContent(), commentsEntity.getCreatedAt(),
             commentsEntity.getClientEntityComment()
                     .getId(), commentsEntity.getClientEntityComment()
                     .getId());
    }
}
