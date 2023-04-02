package ttcs.btl.service.feedbacks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.model.feedbacks.FeedbackEntity;
import ttcs.btl.repository.feedbacks.IFeedbackRepo;

@Service
@RequiredArgsConstructor
public class FeedbackService implements IFeedbackService {

    private final IFeedbackRepo iFeedbackRepo;
    @Override
    public Boolean saveFeedback(FeedbackEntity feedbackEntity) {
        iFeedbackRepo.save(feedbackEntity);
        return true;
    }
}
