package ttcs.btl.repository.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.news.NewsEntity;

@Repository
public interface INewsRepo extends JpaRepository<NewsEntity, Long> {

}
