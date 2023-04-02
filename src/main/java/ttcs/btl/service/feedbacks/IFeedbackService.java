package ttcs.btl.service.feedbacks;

import ttcs.btl.model.feedbacks.FeedbackEntity;

public interface IFeedbackService {
    Boolean saveFeedback(FeedbackEntity feedbackEntity);
}
