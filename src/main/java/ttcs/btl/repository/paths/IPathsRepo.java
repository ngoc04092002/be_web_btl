package ttcs.btl.repository.paths;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.paths.PathsModal;

@Repository
public interface IPathsRepo extends MongoRepository<PathsModal,String> {
}
