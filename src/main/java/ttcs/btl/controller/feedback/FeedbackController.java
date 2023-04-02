package ttcs.btl.controller.feedback;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ttcs.btl.model.feedbacks.FeedbackEntity;
import ttcs.btl.service.feedbacks.FeedbackService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping("send-feedback")
    public Boolean saveFeedback(@RequestBody FeedbackEntity feedbackEntity){
        return feedbackService.saveFeedback(feedbackEntity);
    }
}
