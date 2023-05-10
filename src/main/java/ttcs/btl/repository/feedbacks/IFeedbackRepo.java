package ttcs.btl.repository.feedbacks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ttcs.btl.dto.feedbacks.FeedbackRepostAmount;
import ttcs.btl.model.feedbacks.FeedbackEntity;

import java.util.List;

@Repository
public interface IFeedbackRepo extends JpaRepository<FeedbackEntity, Long> {
    @Modifying
    @Query("delete from FeedbackEntity u where u.id in ?1")
    void deleteFeedbacksWithIds(List<Long> ids);

    @Modifying
    @Query( value = "select new ttcs.btl.dto.feedbacks.FeedbackRepostAmount(f.type,count(f.id)) from FeedbackEntity f where YEAR(f.createdAt) = YEAR(current_date) and  month(f.createdAt) = :month group by f.type")
    List<FeedbackRepostAmount> countFeedbackByTypeAndMonth(@Param("month") Integer month);
}
