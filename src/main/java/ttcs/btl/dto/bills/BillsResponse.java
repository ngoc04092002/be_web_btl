package ttcs.btl.dto.bills;

import ttcs.btl.model.bills.BillEntity;

import java.time.LocalDateTime;

public record BillsResponse(Long id, String name, String phone, Long fk_bill_client_id,LocalDateTime createdAt) {
    public BillsResponse(BillEntity billEntity){
        this(billEntity.getId(),
                billEntity.getName(),
                billEntity.getPhone(),
                billEntity.getClientEntityBill().getId(),
                billEntity.getCreatedAt());
    }
}
