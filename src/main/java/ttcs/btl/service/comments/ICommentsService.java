package ttcs.btl.service.comments;

import ttcs.btl.model.QAEntity.QAEntity;
import ttcs.btl.model.comments.CommentsEntity;

import java.util.List;

public interface ICommentsService {
    List<CommentsEntity> getAllComments();

    CommentsEntity saveComment(CommentsEntity commentsEntity);
    String deleteComment(Long id);
}
