package ttcs.btl.controller.feedback;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.dto.feedbacks.FeedbackReportInfo;
import ttcs.btl.model.feedbacks.ClientFeedbackEntity;
import ttcs.btl.model.feedbacks.FeedbackEntity;
import ttcs.btl.service.feedbacks.IFeedbackService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class FeedbackController {

    private final IFeedbackService iFeedbackService;

    @PostMapping("send-feedback")
    public Boolean saveFeedback(@RequestBody FeedbackEntity feedbackEntity) {
        return iFeedbackService.saveFeedback(feedbackEntity);
    }

    @PostMapping("send-client-feedback")
    public Boolean saveClientFeedback(@RequestBody ClientFeedbackEntity clientFeedbackEntity) {
        return iFeedbackService.saveClientFeedback(clientFeedbackEntity);
    }

    @GetMapping("get-client-feedback")
    public List<ClientFeedbackEntity> getCLientFeedbackById(@RequestParam Long id) {
        return iFeedbackService.getClientFeedbackById(id);
    }

    @GetMapping("get-all-feedback")
    public List<FeedbackEntity> getAllFeedback() {
        return iFeedbackService.getAllFeedback();
    }

    @GetMapping("feedback-report-info")
    public FeedbackReportInfo getFeedbackReportInfo() {
        return iFeedbackService.countFeedbackByTypeAndMonth();
    }

    @PostMapping("delete-feedback-ids")
    public Boolean deleteFeedbackIds(@RequestBody List<Long> ids) {
        try {
            iFeedbackService.deleteFeedbacksWithIds(ids);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @DeleteMapping("delete-client-feedback/{id}")
    public Boolean deleteClientFeedback(@PathVariable Long id) {
        try {
            iFeedbackService.deleteClientFeedbackById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
