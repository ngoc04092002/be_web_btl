package ttcs.btl.service.dailyPosts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.model.dailyPost.DailyPostEntity;
import ttcs.btl.repository.dailyPosts.DailyPostsRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyPostService implements IDailyPostService {

    private final DailyPostsRepo dailyPostsRepo;

    @Override
    public List<DailyPostEntity> getDailyPosts()
    {
        System.out.println(dailyPostsRepo.findAll());
        return dailyPostsRepo.findAll();
    }
}
