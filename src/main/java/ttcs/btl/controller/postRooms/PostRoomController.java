package ttcs.btl.controller.postRooms;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.dto.postRoom.PostRoomResponse;
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

    @GetMapping("get-post_room-report")
    public PostRoomResponse getPostRoomReport(@RequestParam Long id) {
        return iPostRoomService.getPostRoomReport(id);
    }

    @GetMapping("get-post_room-amount")
    public List<Float> getPostRoomAmountByMonth(@RequestParam Long id) {
        return iPostRoomService.getPostRoomAmountByMonth(id);
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
        return iPostRoomService.filterPostRoom(limit, offset, s, address, type, price, acreage, numberRoom, time);
    }

    @GetMapping("get-post_room-user")
    public List<PostRoomEntity> getAllPostRoomOfUser(@RequestParam Long id) {
        return iPostRoomService.getAllPostRoomOfUser(id);
    }

    @PostMapping("save-post-room")
    public Boolean savePostRoom(@RequestBody PostRoomEntity postRoomEntity) {
        return iPostRoomService.savePostRoom(postRoomEntity);
    }

    @PutMapping("update-post-room")
    public PostRoomEntity updatePostRoom(@RequestBody PostRoomEntity postRoomEntity) {
        return iPostRoomService.updatePostRoom(postRoomEntity);
    }

    @DeleteMapping("delete-post-room/{id}")
    public Boolean deletePostRoom(@PathVariable Long id) {
        return iPostRoomService.deletePostRoom(id);
    }
}
