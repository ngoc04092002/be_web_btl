package ttcs.btl.model.QAEntity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Embeddable
public class LikesQAEntity {
    private Long client_id;
    private Long post_id;
}
