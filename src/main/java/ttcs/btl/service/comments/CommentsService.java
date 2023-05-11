package ttcs.btl.service.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.model.comments.CommentsEntity;
import ttcs.btl.repository.comments.ICommentsRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService implements ICommentsService{

    private final ICommentsRepo iCommentsRepo;
    @Override
    public List<CommentsEntity> getAllComments() {
        return iCommentsRepo.findAll();
    }

    @Override
    public Boolean saveComment(CommentsEntity commentsEntity) {
        try {
            iCommentsRepo.save(commentsEntity);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public String deleteComment(Long id) {
        iCommentsRepo.deleteById(id);
        return "delete successfully";
    }
}
