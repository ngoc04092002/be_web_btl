package ttcs.btl.controller.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.model.comments.CommentsEntity;
import ttcs.btl.service.comments.ICommentsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/", produces = "application/json")
public class CommentController {

    private final ICommentsService iCommentsService;

    @GetMapping("get-all-comments")
    public List<CommentsEntity> getAllComments(){
        List<CommentsEntity> commentsEntities = iCommentsService.getAllComments();

        return commentsEntities;
    }

    @PostMapping("save-comment")
    public Boolean saveComment(@RequestBody CommentsEntity commentsEntity){
        return iCommentsService.saveComment(commentsEntity);
    }

    @DeleteMapping("delete-comment/{id}")
    public String deleteComment(@PathVariable("id") Long id){
        return iCommentsService.deleteComment(id);
    }

}
