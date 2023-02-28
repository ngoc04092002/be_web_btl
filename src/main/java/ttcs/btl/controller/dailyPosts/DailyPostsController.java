package ttcs.btl.controller.dailyPosts;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ttcs.btl.dto.dailyPosts.DailyPostResponse;
import ttcs.btl.model.dailyPost.DailyPostEntity;
import ttcs.btl.service.dailyPosts.IDailyPostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/daily-post", produces = "application/json")
public class DailyPostsController {
    private final IDailyPostService iDailyPostService;

    @GetMapping
    public List<DailyPostResponse> getAllDailyPost(){
        List<DailyPostEntity> dailyPostEntities = iDailyPostService.getDailyPosts();

        return dailyPostEntities.stream().map(this::dailyPostResponse).toList();
    }

    private DailyPostResponse dailyPostResponse(DailyPostEntity dailyPostEntity){
        return new DailyPostResponse(dailyPostEntity);
    }
}
