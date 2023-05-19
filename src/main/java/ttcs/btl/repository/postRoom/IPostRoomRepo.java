package ttcs.btl.repository.postRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttcs.btl.model.postRoom.PostRoomEntity;

import java.util.List;

@Repository
public interface IPostRoomRepo extends JpaRepository<PostRoomEntity, Long> {

    PostRoomEntity getById(Long id);

    void deleteById(Long id);

    List<PostRoomEntity> getAllByClientEntityPostRoom_Id(Long id);

    List<PostRoomEntity> getAllByTitleContainsIgnoreCaseOrDesContainsIgnoreCaseOrAddressContainsIgnoreCase(String title, String des, String address);
}
