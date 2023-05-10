package ttcs.btl.service.feedbacks;

import ttcs.btl.dto.feedbacks.FeedbackReportInfo;
import ttcs.btl.model.feedbacks.FeedbackEntity;

import java.util.List;

public interface IFeedbackService {
    Boolean saveFeedback(FeedbackEntity feedbackEntity);

    List<FeedbackEntity> getAllFeedback();

    void deleteFeedbacksWithIds(List<Long> ids);

    FeedbackReportInfo countFeedbackByTypeAndMonth();
}
