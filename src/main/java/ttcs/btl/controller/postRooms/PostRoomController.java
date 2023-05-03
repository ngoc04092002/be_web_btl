package ttcs.btl.controller.postRooms;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.model.postRoom.PostRoomEntity;
import ttcs.btl.service.postRoom.IPostRoomService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/")
public class PostRoomController {

    private final IPostRoomService iPostRoomService;

    @GetMapping("get-all-post-room")
    public List<PostRoomEntity> getAllPostRoom() {
        return iPostRoomService.getAllPostRoom();
    }

    @GetMapping("room-item/{id}")
    public PostRoomEntity getPostRoomById(@PathVariable Long id) {
        return iPostRoomService.getPostRoomById(id);
    }

    @GetMapping("filter-post-room")
    public List<PostRoomEntity> filterPostRoom(@RequestParam(defaultValue = "0") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "") String s,
            @RequestParam(required = false) String address, @RequestParam(required = false) String type,
            @RequestParam(required = false) String price, @RequestParam(required = false) String acreage,
            @RequestParam(required = false) String numberRoom, @RequestParam(required = false) String time) {
        return iPostRoomService.filterPostRoom(limit, offset, s,address, type, price, acreage, numberRoom, time);
    }

    @PostMapping("save-post-room")
    public Boolean savePostRoom(@RequestBody PostRoomEntity postRoomEntity) {
        return iPostRoomService.saveNews(postRoomEntity);
    }

    @PutMapping("update-post-room")
    public Boolean updatePostRoom(@RequestBody PostRoomEntity postRoomEntity) {
        return iPostRoomService.saveNews(postRoomEntity);
    }
}
