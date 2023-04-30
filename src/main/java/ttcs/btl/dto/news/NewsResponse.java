package ttcs.btl.dto.news;

import ttcs.btl.model.news.NewsEntity;
import ttcs.btl.model.news.NewsPiece;

import java.time.LocalDateTime;
import java.util.List;

public record NewsResponse(Long id, String img, String title, String des, String topic, String type,
                           List<NewsPiece> newsBody, LocalDateTime createdAt, LocalDateTime updatedAt,
                           Long fk_news_client_id) {
    public NewsResponse(NewsEntity newsEntity) {
        this(newsEntity.getId(), newsEntity.getImg(), newsEntity.getTitle(), newsEntity.getDes(), newsEntity.getTopic(),
             newsEntity.getType(), newsEntity.getNewsBody(), newsEntity.getCreatedAt(), newsEntity.getUpdatedAt(),
             newsEntity.getClientEntity()
                     .getId());
    }
}
