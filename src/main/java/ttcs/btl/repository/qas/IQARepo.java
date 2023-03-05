package ttcs.btl.repository.qas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.QAEntity.QAEntity;

@Repository
public interface IQARepo extends JpaRepository<QAEntity, Long> {
}
