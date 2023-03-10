package ttcs.btl.controller.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.dto.comments.CommentsResponse;
import ttcs.btl.dto.news.NewsResponse;
import ttcs.btl.model.comments.CommentsEntity;
import ttcs.btl.model.news.NewsEntity;
import ttcs.btl.service.comments.ICommentsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/", produces = "application/json")
public class CommentController {

    private final ICommentsService iCommentsService;

    @GetMapping("get-all-comments")
    public List<CommentsResponse> getAllComments(){
        List<CommentsEntity> commentsEntities = iCommentsService.getAllComments();

        return commentsEntities.stream().map(this::commentsResponse).toList();
    }

    @PostMapping("save-comment")
    public CommentsEntity saveComment(@RequestBody CommentsEntity commentsEntity){
        return iCommentsService.saveComment(commentsEntity);
    }

    @DeleteMapping("delete-comment/{id}")
    public String deleteComment(@PathVariable("id") Long id){
        return iCommentsService.deleteComment(id);
    }

    private CommentsResponse commentsResponse(CommentsEntity commentsEntity){
        return new CommentsResponse(commentsEntity);
    }
}
