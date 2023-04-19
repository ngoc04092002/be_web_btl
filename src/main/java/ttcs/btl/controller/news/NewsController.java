package ttcs.btl.controller.news;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.dto.news.NewsRequest;
import ttcs.btl.dto.news.NewsResponse;
import ttcs.btl.model.news.NewsEntity;
import ttcs.btl.service.news.INewsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/", produces = "application/json")
public class NewsController {
    private final INewsService iNewsService;

    @GetMapping("getAll-news-post")
    public List<NewsResponse> getAllNews(){
        List<NewsEntity> dailyPostEntities = iNewsService.getNews();

        return dailyPostEntities.stream().map(this::newsResponse).toList();
    }

    @PostMapping("save-news")
    public Boolean saveNewsPost(@RequestBody NewsEntity newsEntity){
        return iNewsService.saveNews(newsEntity);
    }

    private NewsResponse newsResponse(NewsEntity newsEntity){
        return new NewsResponse(newsEntity);
    }
}
