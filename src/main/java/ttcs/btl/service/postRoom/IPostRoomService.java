package ttcs.btl.service.postRoom;


import ttcs.btl.model.postRoom.PostRoomEntity;

import java.util.List;

public interface IPostRoomService {
    Boolean saveNews(PostRoomEntity postRoomEntity);

    PostRoomEntity getPostRoomById(Long id);

    List<PostRoomEntity> getAllPostRoom();

    List<PostRoomEntity> filterPostRoom(Integer limit, Integer offset, String s,String address, String type,
            String price, String acreage, String numberRoom,
            String time);

}
