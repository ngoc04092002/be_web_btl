package ttcs.btl.repository.dailyPosts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.dailyPost.DailyPostEntity;

@Repository
public interface DailyPostsRepo extends JpaRepository<DailyPostEntity, Long> {

}
