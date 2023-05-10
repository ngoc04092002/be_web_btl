package ttcs.btl.service.feedbacks;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.dto.feedbacks.FeedbackReportInfo;
import ttcs.btl.dto.feedbacks.FeedbackRepostAmount;
import ttcs.btl.dto.feedbacks.FeedbackResponse;
import ttcs.btl.model.feedbacks.FeedbackEntity;
import ttcs.btl.repository.feedbacks.IFeedbackRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedbackService implements IFeedbackService {

    private final IFeedbackRepo iFeedbackRepo;

    @Override
    public Boolean saveFeedback(FeedbackEntity feedbackEntity) {
        iFeedbackRepo.save(feedbackEntity);
        return true;
    }

    @Override
    public List<FeedbackEntity> getAllFeedback() {
        return iFeedbackRepo.findAll();
    }

    @Override
    public void deleteFeedbacksWithIds(List<Long> ids) {
        iFeedbackRepo.deleteFeedbacksWithIds(ids);
    }

    @Override
    public FeedbackReportInfo countFeedbackByTypeAndMonth() {
        List<FeedbackResponse> smile = new ArrayList<>();
        List<FeedbackResponse> meh = new ArrayList<>();
        List<FeedbackResponse> frown = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            final var r= iFeedbackRepo.countFeedbackByTypeAndMonth(i);
            for(FeedbackRepostAmount fr:r){
                switch (fr.getType()) {
                    case "smile" -> {
                        FeedbackResponse feedbackSmile = new FeedbackResponse();
                        feedbackSmile.setMonth(i);
                        feedbackSmile.setAmount(fr.getAmount());
                        smile.add(feedbackSmile);
                    }
                    case "meh" -> {
                        FeedbackResponse feedbackMeh = new FeedbackResponse();
                        feedbackMeh.setMonth(i);
                        feedbackMeh.setAmount(fr.getAmount());
                        meh.add(feedbackMeh);
                    }
                    case "frown" -> {
                        FeedbackResponse feedbackFrown = new FeedbackResponse();
                        feedbackFrown.setMonth(i);
                        feedbackFrown.setAmount(fr.getAmount());
                        frown.add(feedbackFrown);
                    }
                }
            }
        }


        return new FeedbackReportInfo(smile, meh, frown);
    }
}
