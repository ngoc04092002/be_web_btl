package ttcs.btl.service.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.model.comments.CommentChild;
import ttcs.btl.model.comments.CommentsEntity;
import ttcs.btl.repository.comments.ICommentChildRepo;
import ttcs.btl.repository.comments.ICommentsRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService implements ICommentsService {

    private final ICommentsRepo iCommentsRepo;
    private final ICommentChildRepo iCommentChildRepo;

    @Override
    public List<CommentsEntity> getAllComments() {
        return iCommentsRepo.findAll();
    }

    @Override
    public CommentsEntity saveComment(CommentsEntity commentsEntity) {
        return iCommentsRepo.save(commentsEntity);
    }

    @Override
    public Boolean deleteComment(Long id) {
        try {
            iCommentsRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println("comments===>" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteCommentChild(Long id) {
        try {
            iCommentChildRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println("comments===>" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CommentChild createCommentChild(CommentChild commentChild) {
        return iCommentChildRepo.save(commentChild);
    }
}
