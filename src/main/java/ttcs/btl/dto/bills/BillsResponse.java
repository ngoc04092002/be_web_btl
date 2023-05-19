package ttcs.btl.dto.bills;

import ttcs.btl.model.bills.BillEntity;
import ttcs.btl.model.client.ClientEntity;

import java.time.LocalDateTime;

public record BillsResponse(Long id, String name, String phone, Long rid, ClientEntity clientEntityBill,
                            LocalDateTime createdAt) {
    public BillsResponse(BillEntity billEntity) {
        this(billEntity.getId(), billEntity.getName(), billEntity.getPhone(), billEntity.getRid(),
             billEntity.getClientEntityBill(), billEntity.getCreatedAt());
    }
}