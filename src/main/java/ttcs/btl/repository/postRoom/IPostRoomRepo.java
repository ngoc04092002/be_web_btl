package ttcs.btl.repository.postRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.postRoom.PostRoomEntity;

import java.util.List;

@Repository
public interface IPostRoomRepo extends JpaRepository<PostRoomEntity, Long> {

    PostRoomEntity getById(Long id);

    void deleteById(Long id);

    List<PostRoomEntity> getAllByClientEntityPostRoom_Id(Long id);

    @Modifying
    @Query("select p.id from PostRoomEntity p")
    List<Long> getIds();

    List<PostRoomEntity> getAllByTitleContainsIgnoreCaseOrDesContainsIgnoreCaseOrAddressContainsIgnoreCase(String title, String des, String address);
}
