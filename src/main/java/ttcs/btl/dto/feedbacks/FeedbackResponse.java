package ttcs.btl.dto.feedbacks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeedbackResponse {
    private Integer month;
    private Long amount;
}
