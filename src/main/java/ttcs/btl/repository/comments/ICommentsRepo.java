package ttcs.btl.repository.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.comments.CommentsEntity;

@Repository
public interface ICommentsRepo extends JpaRepository<CommentsEntity, Long> {
}
