package ttcs.btl.service.feedbacks;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.dto.feedbacks.FeedbackReportInfo;
import ttcs.btl.dto.feedbacks.FeedbackRepostAmount;
import ttcs.btl.dto.feedbacks.FeedbackResponse;
import ttcs.btl.model.feedbacks.ClientFeedbackEntity;
import ttcs.btl.model.feedbacks.FeedbackEntity;
import ttcs.btl.repository.feedbacks.IClientFeedbackRepo;
import ttcs.btl.repository.feedbacks.IFeedbackRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedbackService implements IFeedbackService {

    private final IFeedbackRepo iFeedbackRepo;
    private final IClientFeedbackRepo iClientFeedbackRepo;

    @Override
    public Boolean saveFeedback(FeedbackEntity feedbackEntity) {
        try{
            iFeedbackRepo.save(feedbackEntity);
            return true;
        }catch (Exception ex){
            return false;
        }

    }

    @Override
    public Boolean saveClientFeedback(ClientFeedbackEntity clientFeedbackEntity) {
        try{
            iClientFeedbackRepo.save(clientFeedbackEntity);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public List<ClientFeedbackEntity> getClientFeedbackById(Long id) {
        return iClientFeedbackRepo.getAllByClientId(id);
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
