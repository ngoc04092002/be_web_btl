package ttcs.btl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttcs.btl.dto.TestHibernateResponse;
import ttcs.btl.model.TestHibernateEntity;
import ttcs.btl.repository.TestHibernateRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestHibernateService implements ITestHibernateService{
    private final TestHibernateRepo testHibernateRepol;
    @Override
    public List<TestHibernateResponse> getE() {
        List<TestHibernateEntity> hibernateEntities = testHibernateRepol.findAll();

        return hibernateEntities.stream().map(this::mapToTestHibernateResponse).toList();
    }

    private TestHibernateResponse mapToTestHibernateResponse(TestHibernateEntity hibernateEntity) {
        return new TestHibernateResponse(hibernateEntity);
    }
}
