package ttcs.btl.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.TestMongo;

@Repository
public interface TestMongoRepo extends MongoRepository<TestMongo, String> {
}
