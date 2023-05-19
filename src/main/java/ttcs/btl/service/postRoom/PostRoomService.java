package ttcs.btl.service.postRoom;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ttcs.btl.model.postRoom.PostRoomEntity;
import ttcs.btl.repository.postRoom.IPostRoomRepo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PostRoomService implements IPostRoomService {

    private final IPostRoomRepo iPostRoomRepo;

    @Override
    public List<PostRoomEntity> getAllPostRoom() {
        return iPostRoomRepo.findAll();
    }

    @Override
    public List<PostRoomEntity> filterPostRoom(Integer limit, Integer offset, String s, String address, String type,
            String price, String acreage, String numberRoom, String time) {
        List<PostRoomEntity> postRooms;
        if (StringUtils.isBlank(s)) {
            if (limit != 0) {
                postRooms = getAllPostRoom().stream()
                        .limit(limit)
                        .toList();
            } else {
                postRooms = getAllPostRoom();
            }
        } else {
            postRooms = searchAllPostRooms(limit, offset, s);
        }

        postRooms = getPostRoomEntitiesContainNotNull(address, postRooms);
        postRooms = getPostRoomEntitiesContainNotNull(type, postRooms);
        postRooms = getPostRoomEntitiesContainNotNull(numberRoom, postRooms);

        postRooms = getPostRoomEntitiesEstimate(price, postRooms);
        postRooms = getPostRoomEntitiesEstimate(acreage, postRooms);

        if (StringUtils.isNotBlank(time)) {
            postRooms = postRooms.stream()
                    .filter(p -> {
                        LocalDateTime now = LocalDateTime.now();
                        Duration duration = Duration.between(p.getCreatedAt(), now);
                        long diff = duration.toDays();
                        return diff <= Integer.parseInt(time);
                    })
                    .toList();
        }


        return postRooms;
    }

    public List<PostRoomEntity> searchAllPostRooms(Integer limit, Integer offset, String s) {
        List<PostRoomEntity> postRoomEntities = iPostRoomRepo.getAllByTitleContainsIgnoreCaseOrDesContainsIgnoreCaseOrAddressContainsIgnoreCase(
                s, s, s);
        if (limit != 0) {
            postRoomEntities = postRoomEntities.stream()
                    .limit(limit)
                    .toList();
        }
        if (offset != 0) {
            postRoomEntities = postRoomEntities.stream()
                    .skip(offset)
                    .toList();
        }

        return postRoomEntities;
    }

    @Override
    public Boolean savePostRoom(PostRoomEntity postRoomEntity) {
        try {
            iPostRoomRepo.save(postRoomEntity);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean deletePostRoom(Long id) {
        try {
            iPostRoomRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public PostRoomEntity getPostRoomById(Long id) {
        return iPostRoomRepo.getById(id);
    }

    @Override
    public PostRoomEntity updatePostRoom(PostRoomEntity postRoomEntity) {
        final var postRoom = iPostRoomRepo.getById(postRoomEntity.getId());
        postRoom.setStatus(postRoomEntity.getStatus());
        postRoom.setSale(postRoomEntity.getSale());
        postRoom.setBedRoom(postRoomEntity.getBedRoom());
        postRoom.setClientEntityPostRoom(postRoomEntity.getClientEntityPostRoom());
        postRoom.setRoomType(postRoomEntity.getRoomType());
        postRoom.setAcreage(postRoomEntity.getAcreage());
        postRoom.setAddress(postRoomEntity.getAddress());
        postRoom.setBathroom(postRoomEntity.getBathroom());
        postRoom.setDes(postRoomEntity.getDes());
        postRoom.setLimitNumberPeople(postRoomEntity.getLimitNumberPeople());
        postRoom.setPhone(postRoomEntity.getPhone());
        postRoom.setPrice(postRoomEntity.getPrice());
        postRoom.setSrc(postRoomEntity.getSrc());
        postRoom.setTitle(postRoomEntity.getTitle());

        return iPostRoomRepo.save(postRoom);
    }

    @Override
    public List<PostRoomEntity> getAllPostRoomOfUser(Long id) {
        return iPostRoomRepo.getAllByClientEntityPostRoom_Id(id);
    }

    private List<PostRoomEntity> getPostRoomEntitiesContainNotNull(String filter, List<PostRoomEntity> postRooms) {
        if (StringUtils.isNotBlank(filter)) {
            postRooms = postRooms.stream()
                    .filter(p -> p.getBedRoom()
                            .contains(filter))
                    .toList();
        }
        return postRooms;
    }

    private List<PostRoomEntity> getPostRoomEntitiesEstimate(String price, List<PostRoomEntity> postRooms) {
        if (StringUtils.isNotBlank(price)) {
            List<Integer> lis = new ArrayList<>();
            Pattern pt = Pattern.compile("\\d+");
            Matcher m = pt.matcher(price);
            boolean isGreater = price.startsWith(">");
            while (m.find()) {
                lis.add(Integer.parseInt(m.group()));
            }
            postRooms = postRooms.stream()
                    .filter(p -> {
                        if (lis.size() == 2) {
                            return Integer.parseInt(p.getPrice()) >= lis.get(0) && Integer.parseInt(
                                    p.getPrice()) <= lis.get(1);
                        } else {
                            if (isGreater) {
                                return Integer.parseInt(p.getPrice()) > lis.get(0);
                            }
                            return Integer.parseInt(p.getPrice()) < lis.get(0);
                        }
                    })
                    .toList();
        }
        return postRooms;
    }
}
