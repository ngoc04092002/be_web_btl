package ttcs.btl.repository.feedbacks;

import org.springframework.data.jpa.repository.JpaRepository;
import ttcs.btl.model.feedbacks.ClientFeedbackEntity;

import java.util.List;

public interface IClientFeedbackRepo extends JpaRepository<ClientFeedbackEntity,Long> {
    List<ClientFeedbackEntity> getAllByClientId(Long id);

    void deleteById(Long id);
}
