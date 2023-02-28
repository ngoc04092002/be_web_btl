package ttcs.btl.dto.dailyPosts;

import ttcs.btl.model.dailyPost.DailyPostEntity;

import java.util.Date;
import java.util.List;

public record DailyPostResponse(Long id, String img, String title, String des, String posterName, List<String> likes,
                                List<String> favorites, Date createdAt) {
    public DailyPostResponse(DailyPostEntity dailyPostEntity) {
        this(dailyPostEntity.getId(), dailyPostEntity.getImg(), dailyPostEntity.getTitle(),
             dailyPostEntity.getPosterName(), dailyPostEntity.getDes(), dailyPostEntity.getLikes(),
             dailyPostEntity.getFavorites(), dailyPostEntity.getCreatedAt());
    }
}
