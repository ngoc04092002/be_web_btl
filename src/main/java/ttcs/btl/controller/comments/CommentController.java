package ttcs.btl.controller.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.model.comments.CommentChild;
import ttcs.btl.model.comments.CommentsEntity;
import ttcs.btl.service.comments.ICommentsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/", produces = "application/json")
public class CommentController {

    private final ICommentsService iCommentsService;
    private final Integer delayTime = 1500; //1.5s

    @GetMapping("get-all-comments")
    public List<CommentsEntity> getAllComments() {
        List<CommentsEntity> commentsEntities = iCommentsService.getAllComments();

        return commentsEntities;
    }

    @PostMapping("save-comment")
    public CommentsEntity saveComment(@RequestBody CommentsEntity commentsEntity) throws InterruptedException {
        Thread.sleep(delayTime);
        return iCommentsService.saveComment(commentsEntity);
    }

    @PostMapping("save-comment-child")
    public CommentChild saveCommentChild(@RequestBody CommentChild commentChild) throws InterruptedException {
        Thread.sleep(delayTime);
        return iCommentsService.createCommentChild(commentChild);
    }

    @DeleteMapping("delete-comment/{id}")
    public Boolean deleteComment(@PathVariable("id") Long id) {
        return iCommentsService.deleteComment(id);
    }

    @DeleteMapping("delete-comment-child/{id}")
    public Boolean deleteCommentChild(@PathVariable("id") Long id) {
        return iCommentsService.deleteCommentChild(id);
    }

}
