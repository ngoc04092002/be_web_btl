package ttcs.btl.service.news;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.dto.news.NewsResponse;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.model.news.NewsEntity;
import ttcs.btl.repository.clients.IClientRepo;
import ttcs.btl.repository.news.INewsRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsService implements INewsService {

    private final INewsRepo INewsRepo;
    private final IClientRepo iClientRepo;

    @Override
    public List<NewsEntity> getNews()
    {
        return INewsRepo.findAll();
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
}
