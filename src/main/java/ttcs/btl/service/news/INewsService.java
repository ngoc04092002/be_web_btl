package ttcs.btl.service.news;

import ttcs.btl.dto.news.NewsResponse;
import ttcs.btl.model.news.NewsEntity;

import java.util.List;

public interface INewsService {
    List<NewsEntity> getNews();
    Boolean saveNews(NewsEntity newsEntity);
}
