package ttcs.btl.repository.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.news.NewsEntity;

import java.util.List;

@Repository
public interface INewsRepo extends JpaRepository<NewsEntity, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "SELECT * FROM NEWS LIMIT :limit OFFSET :offset")
    List<NewsEntity> getAllNewsAndLimit(@Param("limit") Integer limit, @Param("offset") Integer offset);

    List<NewsEntity> getByTopic(String topic);

    List<NewsEntity> getByTopicAndType(String topic, String type);

    List<NewsEntity> getByTitleContainingIgnoreCaseOrDesContainingIgnoreCase(String title, String des);

    NewsEntity getById(Long id);

    @Modifying
    @Query("delete from NewsEntity e where e.id in ?1")
    void deleteNewsWithIds(List<Long> ids);
}
