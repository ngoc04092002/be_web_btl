package ttcs.btl.controller.qas;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.dto.news.NewsResponse;
import ttcs.btl.dto.qas.QAResponse;
import ttcs.btl.model.QAEntity.QAEntity;
import ttcs.btl.model.news.NewsEntity;
import ttcs.btl.service.qas.IQAService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/", produces = "application/json")
public class QAController {
    private final IQAService iqaService;

    @GetMapping("get-all-QA")
    public List<QAResponse> getAllQa(){
        List<QAEntity> qaEntities = iqaService.getAllQA();
        return qaEntities.stream().map(this::QAResponse).toList();
    }

    @PostMapping("save-QA")
    public QAEntity saveQa(@RequestBody QAEntity qaEntity){
        return iqaService.saveQA(qaEntity);
    }

    @DeleteMapping("delete-QA/{id}")
    public String deleteQa(@PathVariable("id") Long id){
        return iqaService.deleteQA(id);
    }

    private QAResponse QAResponse(QAEntity qaEntity){
        return new QAResponse(qaEntity);
    }
}
