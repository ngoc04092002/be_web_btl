package ttcs.btl.service.news;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.dto.news.NewsResponse;
import ttcs.btl.model.news.NewsEntity;
import ttcs.btl.repository.news.INewsRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsService implements INewsService {

    private final INewsRepo INewsRepo;

    @Override
    public List<NewsEntity> getNews()
    {
        return INewsRepo.findAll();
    }

    @Override
    public NewsResponse saveNews(NewsEntity newsEntity) {
        NewsEntity news = INewsRepo.save(newsEntity);
        return new NewsResponse(news);
    }
}
