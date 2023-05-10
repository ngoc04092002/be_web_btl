package ttcs.btl.controller.feedback;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.dto.feedbacks.FeedbackReportInfo;
import ttcs.btl.model.feedbacks.FeedbackEntity;
import ttcs.btl.service.feedbacks.IFeedbackService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class FeedbackController {

    private final IFeedbackService iFeedbackService;

    @PostMapping("send-feedback")
    public Boolean saveFeedback(@RequestBody FeedbackEntity feedbackEntity){
        return iFeedbackService.saveFeedback(feedbackEntity);
    }

    @GetMapping("get-all-feedback")
    public List<FeedbackEntity> getAllFeedback(){
        return iFeedbackService.getAllFeedback();
    }

    @GetMapping("feedback-report-info")
    public FeedbackReportInfo getFeedbackReportInfo(){
        return  iFeedbackService.countFeedbackByTypeAndMonth();
    }

    @PostMapping("delete-feedback-ids")
    public Boolean deleteFeedbackIds(@RequestBody List<Long> ids){
        try{
            iFeedbackService.deleteFeedbacksWithIds(ids);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
