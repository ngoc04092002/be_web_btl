package ttcs.btl.controller.news;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.dto.news.NewsResponse;
import ttcs.btl.model.news.NewsEntity;
import ttcs.btl.service.news.INewsService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/")
public class NewsController {
    private final INewsService iNewsService;

    @GetMapping("getAll-news-post")
    public List<NewsResponse> getAllNews() {
        List<NewsEntity> dailyPostEntities = iNewsService.getNews();

        return dailyPostEntities.stream()
                .map(this::newsResponse)
                .toList();
    }

    @GetMapping("get-news-post-limit")
    public List<NewsResponse> filterNews(@RequestParam(defaultValue = "0") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "") String topic,
            @RequestParam(defaultValue = "") String type) {
        List<NewsEntity> dailyPostEntities = new ArrayList<>();
        if (StringUtils.isBlank(type) && StringUtils.isBlank(topic)) {
            dailyPostEntities = iNewsService.getNewsAndLimit(limit.intValue(), offset.intValue());
        } else if (StringUtils.isNotBlank(type)) {
            dailyPostEntities = iNewsService.getByType(limit, offset, topic, type);

        } else if (StringUtils.isNotBlank(topic)) {
            dailyPostEntities = iNewsService.getByTopic(limit, offset, topic);

        }

        return dailyPostEntities.stream()
                .map(this::newsResponse)
                .toList();
    }

    @GetMapping("search-news")
    public List<NewsEntity> searchNews(@RequestParam(defaultValue = "0") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "") String s) {
        List<NewsEntity> news;
        if (StringUtils.isBlank(s)) {
            if(limit!=0){
                news = iNewsService.getNews().stream().limit(limit).toList();
            }else{
                news = iNewsService.getNews();
            }
        } else {
            news = iNewsService.searchNews(limit, offset, s);
        }
        return news;
    }

    @GetMapping("news/{id}")
    public NewsEntity getNewsById(@PathVariable Long id){
        System.out.println(id);
        return iNewsService.getById(id);
    }

    @PostMapping("save-news")
    public Boolean saveNewsPost(@RequestBody NewsEntity newsEntity) {
        return iNewsService.saveNews(newsEntity);
    }

    @PostMapping("delete-news-ids")
    public Boolean deleteFeedbackIds(@RequestBody List<Long> ids){
        System.out.println(ids);
        try{
            iNewsService.deleteNewsWithIds(ids);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    private NewsResponse newsResponse(NewsEntity newsEntity) {
        return new NewsResponse(newsEntity);
    }
}
