package ttcs.btl.model.comments;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Embeddable
public class LikeCommentEntity {
    private Long client_id;
    private Long post_id;
    private Long comment_id;
}
