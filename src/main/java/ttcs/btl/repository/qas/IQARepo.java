package ttcs.btl.repository.qas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.QAEntity.QAEntity;

import java.util.List;

@Repository
public interface IQARepo extends JpaRepository<QAEntity, Long> {


    @Modifying
    @Query(nativeQuery = true, value = "select * from qas where content like CONCAT('%',:s,'%') order by created_at DESC " + "limit " +
            ":limit offset :offset")
    List<QAEntity> filterQA(@Param("s") String s, @Param("limit") Integer limit, @Param("offset") Integer offset);
}
