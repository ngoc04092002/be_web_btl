package ttcs.btl.dto;


import ttcs.btl.model.TestHibernateEntity;

public record TestHibernateResponse(Long id, String nameTest) {
    public TestHibernateResponse(TestHibernateEntity hibernateEntity){
        this(hibernateEntity.getId(),hibernateEntity.getNameTest());
    }
}
