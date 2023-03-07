package ttcs.btl.dto.news;

import ttcs.btl.model.news.NewsEntity;

import java.time.LocalDateTime;

public record NewsResponse(Long id, String img, String title, String des, String posterName, LocalDateTime createdAt,
                           LocalDateTime updatedAt, Long fk_news_client_id) {
    public NewsResponse(NewsEntity newsEntity) {
        this(newsEntity.getId(), newsEntity.getImg(), newsEntity.getTitle(), newsEntity.getPosterName(),
             newsEntity.getDes(), newsEntity.getCreatedAt(), newsEntity.getUpdatedAt(), newsEntity.getClientEntity()
                     .getId());
    }
}
