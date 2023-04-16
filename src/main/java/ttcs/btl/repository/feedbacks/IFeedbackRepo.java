package ttcs.btl.repository.feedbacks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.feedbacks.FeedbackEntity;

import java.util.List;

@Repository
public interface IFeedbackRepo extends JpaRepository<FeedbackEntity,Long> {
    @Modifying
    @Query("delete from FeedbackEntity u where u.id in ?1")
    void deleteFeedbacksWithIds(List<Long> ids);
}
