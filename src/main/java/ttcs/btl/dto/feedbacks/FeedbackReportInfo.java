package ttcs.btl.dto.feedbacks;

import java.util.List;


public record FeedbackReportInfo(List<FeedbackResponse> smiles, List<FeedbackResponse> meh, List<FeedbackResponse> frown) {

}
