    package ttcs.btl.dto.feedbacks;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeedbackRepostAmount {
    private String type;
    private Long amount;
}
