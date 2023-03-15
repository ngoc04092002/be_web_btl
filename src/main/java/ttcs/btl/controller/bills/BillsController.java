package ttcs.btl.controller.bills;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.dto.bills.BillsResponse;
import ttcs.btl.model.bills.BillEntity;
import ttcs.btl.service.bills.IBillService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class BillsController {
    private BillsResponse billsResponse(BillEntity billEntity){
        return new BillsResponse(billEntity);
    }
    private final IBillService iBillService;
    @GetMapping("get-all-bills")
    public List<BillsResponse> getAllBills(){
        List<BillEntity> allBills = iBillService.getAllBills();
        return allBills.stream().map(this::billsResponse).toList();
    }

    @PostMapping("save-bill")
    public BillEntity saveBill(@RequestBody BillEntity billEntity) {
        return iBillService.saveBill(billEntity);
    }
}