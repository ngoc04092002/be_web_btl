package ttcs.btl.service.postRoom;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ttcs.btl.dto.postRoom.PostRoomReport;
import ttcs.btl.dto.postRoom.PostRoomResponse;
import ttcs.btl.model.postRoom.PostRoomEntity;
import ttcs.btl.repository.postRoom.IPostRoomRepo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

        postRooms = getPostRoomEntitiesContainNotNull("address", address, postRooms);
        postRooms = getPostRoomEntitiesContainNotNull("type", type, postRooms);
        postRooms = getPostRoomEntitiesContainNotNull("numberRoom", numberRoom, postRooms);

        postRooms = getPostRoomEntitiesEstimate("price", price, postRooms);
        postRooms = getPostRoomEntitiesEstimate("acreage", acreage, postRooms);

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
    public PostRoomResponse getPostRoomReport(Long id) {
        PostRoomReport totalRoom = new PostRoomReport();
        PostRoomReport rented = new PostRoomReport();
        PostRoomReport sales = new PostRoomReport();

        final var postRooms = iPostRoomRepo.getAllByClientEntityPostRoom_Id(id);
        totalRoom.setSales((float) postRooms.size());
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1; // current
        int year = cal.get(Calendar.YEAR);
        final var postRoomsBeforeMonth = postRooms.stream()
                .filter(r -> {
                    LocalDateTime local = LocalDateTime.parse(r.getCreatedAt()
                                                                      .toString());
                    int currentMonth = local.getMonthValue();
                    int currentYear = local.getYear();
                    return currentMonth == month - 1 && year == currentYear;
                })
                .toList();

        totalRoom.setDevelopSpeed(postRooms.size() - postRoomsBeforeMonth.size() + " Phòng");
        totalRoom.setIncrement(true);

        final var rentCurrentMonth = postRooms.stream()
                .filter(r -> {
                    LocalDateTime local = LocalDateTime.parse(r.getCreatedAt()
                                                                      .toString());
                    int currentMonth = local.getMonthValue();
                    int currentYear = local.getYear();
                    return currentMonth == month && !r.getStatus() && currentYear == year;
                })
                .toList();

        final var rentBeforeMonth = postRooms.stream()
                .filter(r -> {
                    LocalDateTime local = LocalDateTime.parse(r.getCreatedAt()
                                                                      .toString());
                    int currentMonth = local.getMonthValue();
                    int currentYear = local.getYear();
                    return currentMonth == month - 1 && !r.getStatus() && currentYear == year;
                })
                .toList();

        rented.setSales((float) (rentCurrentMonth.size()));
        float developSpeed = (rentCurrentMonth.size() - rentBeforeMonth.size()) / (rentBeforeMonth.size() == 0 ? rentCurrentMonth.size() == 0 ? 1 : rentCurrentMonth.size() : rentBeforeMonth.size());
        rented.setDevelopSpeed(String.format("%.2f", Math.abs(developSpeed * 100)) + "%");
        rented.setIncrement(developSpeed < 0 ? false : true);

        float currentSales = getSalesMonthly(postRooms, month, year);
        float beforeSales = getSalesMonthly(postRooms, month - 1, year);
        float salesDevelopSpeed = (currentSales - beforeSales) / (beforeSales == 0 ? currentSales == 0 ? 1 : currentSales : beforeSales);
        sales.setSales(currentSales);
        sales.setDevelopSpeed(String.format("%.2f", Math.abs(salesDevelopSpeed * 100)) + "%");
        sales.setIncrement(salesDevelopSpeed < 0 ? false : true);

        return new PostRoomResponse(totalRoom, rented, sales);
    }

    @Override
    public List<Float> getPostRoomAmountByMonth(Long userId) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        List<Float> monthsData = new ArrayList<Float>();
        final var postRooms = iPostRoomRepo.getAllByClientEntityPostRoom_Id(userId);
        for (int i = 1; i <= 12; i++) {
            float price = getSalesMonthly(postRooms, i, year);
            monthsData.add(price);
        }
        return monthsData;
    }

    @Override
    public List<Long> getIds() {
        return iPostRoomRepo.getIds();
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

    private List<PostRoomEntity> getPostRoomEntitiesContainNotNull(String type, String filter,
            List<PostRoomEntity> postRooms) {
        switch (type) {
            case "address" -> {
                if (StringUtils.isNotBlank(filter)) {
                    postRooms = postRooms.stream()
                            .filter(p -> p.getAddress()
                                    .contains(filter))
                            .toList();
                }
            }
            case "type" -> {
                if (StringUtils.isNotBlank(filter)) {
                    postRooms = postRooms.stream()
                            .filter(p -> p.getRoomType()
                                    .contains(filter))
                            .toList();
                }
            }
            case "numberRoom" -> {
                if (StringUtils.isNotBlank(filter)) {
                    postRooms = postRooms.stream()
                            .filter(p -> p.getBedRoom()
                                    .contains(filter))
                            .toList();
                }
            }
        }

        return postRooms;
    }

    private List<PostRoomEntity> getPostRoomEntitiesEstimate(String type, String price,
            List<PostRoomEntity> postRooms) {
        if (StringUtils.isNotBlank(price)) {
            List<Integer> lis = new ArrayList<>();
            Pattern pt = Pattern.compile("\\d+");
            Matcher m = pt.matcher(price);
            boolean isGreater = price.startsWith(">");
            switch (type) {
                case "price" -> {
                    while (m.find()) {
                        lis.add(Integer.parseInt(m.group()) * 1000000); //triệu
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
                case "acreage" -> {
                    while (m.find()) {
                        lis.add(Integer.parseInt(m.group()));
                    }
                    postRooms = postRooms.stream()
                            .filter(p -> {
                                if (lis.size() == 2) {
                                    return Integer.parseInt(p.getAcreage()) >= lis.get(0) && Integer.parseInt(
                                            p.getAcreage()) <= lis.get(1);
                                } else {
                                    if (isGreater) {
                                        return Integer.parseInt(p.getAcreage()) > lis.get(0);
                                    }
                                    return Integer.parseInt(p.getAcreage()) < lis.get(0);
                                }
                            })
                            .toList();
                }
            }
        }

        return postRooms;
    }

    private Float getSalesMonthly(List<PostRoomEntity> postRooms, int month, int year) {
        float currentSales = 0;
        for (PostRoomEntity p : postRooms) {
            LocalDateTime local = LocalDateTime.parse(p.getCreatedAt()
                                                              .toString());
            int currentMonth = local.getMonthValue();
            int currentYear = local.getYear();
            if (currentMonth == month && currentYear == year) {
                currentSales += Float.parseFloat(p.getPrice());
            }
        }
        return currentSales;
    }
}
