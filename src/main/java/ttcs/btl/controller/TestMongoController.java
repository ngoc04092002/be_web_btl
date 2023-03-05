package ttcs.btl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.model.TestMongo;
import ttcs.btl.repository.TestMongoRepo;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class TestMongoController {
    private final TestMongoRepo testMongoRepo;

    @PostMapping("save-mongo")
    public TestMongo saveTestMongo(@RequestBody TestMongo testMongo){
        return testMongoRepo.save(testMongo);
    }

    @GetMapping("get-mongo")
    public List<TestMongo> getAllTest(){
        return testMongoRepo.findAll();
    }
}
