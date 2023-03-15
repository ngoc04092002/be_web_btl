package ttcs.btl.dto.bills;

import ttcs.btl.model.bills.BillEntity;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.model.postRoom.PostRoomEntity;

import java.time.LocalDateTime;

public record BillsResponse(Long id, PostRoomEntity postRoomEntity, ClientEntity clientEntityBill,
                            LocalDateTime createdAt) {
    public BillsResponse(BillEntity billEntity) {
        this(billEntity.getId(), billEntity.getPostRoomEntity(), billEntity.getClientEntityBill(),
             billEntity.getCreatedAt());
    }
}