package ttcs.btl.service.postRoom;


import ttcs.btl.dto.postRoom.PostRoomResponse;
import ttcs.btl.model.postRoom.PostRoomEntity;

import java.util.List;

public interface IPostRoomService {
    Boolean savePostRoom(PostRoomEntity postRoomEntity);

    Boolean deletePostRoom(Long id);

    PostRoomResponse getPostRoomReport(Long id);

    List<Float> getPostRoomAmountByMonth(Long userId
    );

    List<Long> getIds();

    PostRoomEntity getPostRoomById(Long id);

    PostRoomEntity updatePostRoom(PostRoomEntity postRoomEntity);

    List<PostRoomEntity> getAllPostRoomOfUser(Long id);

    List<PostRoomEntity> getAllPostRoom();

    List<PostRoomEntity> filterPostRoom(Integer limit, Integer offset, String s,String address, String type,
            String price, String acreage, String numberRoom,
            String time);

}
