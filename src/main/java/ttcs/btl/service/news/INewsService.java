package ttcs.btl.service.news;

import ttcs.btl.dto.news.NewsResponse;
import ttcs.btl.model.news.NewsEntity;

import java.util.List;

public interface INewsService {
    List<NewsEntity> getNews();
    List<NewsEntity> getByTopic(Integer limit, Integer offset, String topic);
    List<NewsEntity> getByType(Integer limit, Integer offset,String topic , String type);
    List<NewsEntity> getNewsAndLimit(Integer limit, Integer offset);
    Boolean saveNews(NewsEntity newsEntity);
    List<NewsEntity> searchNews(Integer limit,Integer offset, String s);

    NewsEntity getById(Long id);

    void deleteNewsWithIds(List<Long> ids);
}
