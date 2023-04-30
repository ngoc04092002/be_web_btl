package ttcs.btl.service.news;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.model.news.NewsEntity;
import ttcs.btl.repository.clients.IClientRepo;
import ttcs.btl.repository.news.INewsRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NewsService implements INewsService {

    private final INewsRepo INewsRepo;
    private final IClientRepo iClientRepo;

    @Override
    public List<NewsEntity> getNews() {
        return INewsRepo.findAll();
    }

    @Override
    public List<NewsEntity> getByTopic(Integer limit, Integer offset, String topic) {
        List<NewsEntity> newsEntities = INewsRepo.getByTopic(topic);
        if (limit != 0) {
            newsEntities = newsEntities.stream()
                    .limit(limit)
                    .toList();
        }
        if (offset != 0) {
            newsEntities = newsEntities.stream()
                    .skip(limit)
                    .toList();
        }
        return newsEntities;
    }

    @Override
    public List<NewsEntity> getByType(Integer limit, Integer offset, String topic, String type) {
        List<NewsEntity> newsEntities = INewsRepo.getByTopicAndType(topic, type);
        if (limit != 0) {
            newsEntities = newsEntities.stream()
                    .limit(limit)
                    .toList();
        }
        if (offset != 0) {
            newsEntities = newsEntities.stream()
                    .skip(limit)
                    .toList();
        }
        return newsEntities;
    }

    @Override
    public List<NewsEntity> getNewsAndLimit(Integer limit, Integer offset) {
        return INewsRepo.getAllNewsAndLimit(limit, offset);
    }

    @Override
    public Boolean saveNews(NewsEntity newsEntity) {
        Optional<ClientEntity> clientEntityOptional = iClientRepo.findById(newsEntity.getClientEntity()
                                                                                   .getId());
        if (clientEntityOptional.isPresent()) {
            ClientEntity clientEntity = clientEntityOptional.get();
            newsEntity.setClientEntity(clientEntity);
            INewsRepo.save(newsEntity);
            return true;
        }
        return false;
    }

    @Override
    public List<NewsEntity> searchNews(Integer limit, Integer offset, String s) {
        List<NewsEntity> newsEntities = INewsRepo.getByTitleContainingIgnoreCaseOrDesContainingIgnoreCase(s, s);
        if (limit != 0) {
            newsEntities = newsEntities.stream()
                    .limit(limit)
                    .toList();
        }
        if (offset != 0) {
            newsEntities = newsEntities.stream()
                    .skip(offset)
                    .toList();
        }
        return newsEntities;
    }

    @Override
    public NewsEntity getById(Long id) {
        return INewsRepo.getById(id);
    }

    @Override
    public void deleteNewsWithIds(List<Long> ids) {
        INewsRepo.deleteNewsWithIds(ids);
    }
}
