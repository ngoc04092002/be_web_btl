package ttcs.btl.controller.qas;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.dto.qas.QAResponse;
import ttcs.btl.dto.qas.QAUpdateRequest;
import ttcs.btl.model.QAEntity.LikesQAEntity;
import ttcs.btl.model.QAEntity.QAEntity;
import ttcs.btl.service.qas.IQAService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/api/v1/", produces = "application/json")
public class QAController {
    private final IQAService iqaService;

    @GetMapping("get-all-QA")
    public List<QAEntity> getAllQa() {
        return iqaService.getAllQA(true);
    }

    @GetMapping("get-all-QA-user")
    public List<QAEntity> getAllQAByUser(@RequestParam Long id) {
        return iqaService.getAllQAByUser(id);
    }

    @PutMapping("update-qa")
    public Boolean updateQA(@RequestBody @Valid QAUpdateRequest qaUpdateRequest){
        return iqaService.updateQA(qaUpdateRequest);
    }

    @GetMapping("filter-qa")
    public List<QAEntity> filterQA(@RequestParam(defaultValue = "") String s,
            @RequestParam(defaultValue = "8") Integer limit, @RequestParam(defaultValue = "0") Integer offset) {
        return iqaService.filterQA(s, limit, offset);
    }

    @GetMapping("get-likes")
    public List<LikesQAEntity> getLikes(){
        return iqaService.getLikes();
    }

    @PostMapping("toggle-like")
    public Integer toggleLike(@RequestBody LikesQAEntity likesQAEntity){
        return iqaService.toggleLike(likesQAEntity);
    }

    @PostMapping("save-QA")
    public Boolean saveQa(@RequestBody QAEntity qaEntity) {
        return iqaService.saveQA(qaEntity);
    }

    @DeleteMapping("delete-QA/{id}")
    public Boolean deleteQa(@PathVariable("id") Long id) {
        return iqaService.deleteQA(id);
    }


}
