package ttcs.btl.repository.feedbacks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.feedbacks.FeedbackEntity;

@Repository
public interface IFeedbackRepo extends JpaRepository<FeedbackEntity,Long> {
}
