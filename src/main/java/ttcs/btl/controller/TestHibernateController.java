package ttcs.btl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ttcs.btl.dto.TestHibernateResponse;
import ttcs.btl.service.ITestHibernateService;

import java.util.List;

@RestController
@RequestMapping(value = "/test-hibernate")
@RequiredArgsConstructor
public class TestHibernateController {
    private final ITestHibernateService iHibernateService;

    @GetMapping
    public List<TestHibernateResponse> getEntity(){
        return iHibernateService.getE();
    }
}