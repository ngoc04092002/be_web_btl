package ttcs.btl.controller.bills;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.dto.bills.BillsResponse;
import ttcs.btl.model.bills.BillEntity;
import ttcs.btl.repository.postRoom.IPostRoomRepo;
import ttcs.btl.service.bills.IBillService;
import ttcs.btl.service.postRoom.IPostRoomService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class BillsController {
    private BillsResponse billsResponse(BillEntity billEntity){
        return new BillsResponse(billEntity);
    }
    private final IBillService iBillService;
    private final IPostRoomService iPostRoomService;
    private final IPostRoomRepo iPostRoomRepo;
    @GetMapping("get-all-bills")
    public List<BillsResponse> getAllBills(@RequestParam Long id){
        List<BillEntity> allBills = iBillService.getAllBills(id);
        return allBills.stream().map(this::billsResponse).toList();
    }

    @PostMapping("save-bill")
    public Boolean saveBill(@RequestBody BillEntity billEntity) {
        return iBillService.saveBill(billEntity);
    }

    @PostMapping("verify-rent/{billId}/{rid}")
    public Boolean verifyRent(@PathVariable Long billId,@PathVariable Long rid) {
        try{
            iBillService.deleteBill(billId);
            final var room = iPostRoomService.getPostRoomById(rid);
            room.setStatus(false);
            iPostRoomRepo.save(room);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    @DeleteMapping("delete-bill/{id}")
    public Boolean deleteBill(@PathVariable Long id) {
        return iBillService.deleteBill(id);
    }
}