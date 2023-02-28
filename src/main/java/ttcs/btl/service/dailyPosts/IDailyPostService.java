package ttcs.btl.service.dailyPosts;

import ttcs.btl.model.dailyPost.DailyPostEntity;

import java.util.List;

public interface IDailyPostService {
    List<DailyPostEntity> getDailyPosts();
}
