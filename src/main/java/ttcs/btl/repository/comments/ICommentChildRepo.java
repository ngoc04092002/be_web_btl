package ttcs.btl.repository.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.comments.CommentChild;

@Repository
public interface ICommentChildRepo extends JpaRepository<CommentChild,Long> {
    void deleteById(Long id);
}
