package ttcs.btl.service.comments;

import ttcs.btl.model.QAEntity.QAEntity;
import ttcs.btl.model.comments.CommentChild;
import ttcs.btl.model.comments.CommentsEntity;

import java.util.List;

public interface ICommentsService {
    List<CommentsEntity> getAllComments();

    CommentsEntity saveComment(CommentsEntity commentsEntity);
    Boolean deleteComment(Long id);

    Boolean deleteCommentChild(Long id);

    CommentChild createCommentChild(CommentChild commentChild);
}
