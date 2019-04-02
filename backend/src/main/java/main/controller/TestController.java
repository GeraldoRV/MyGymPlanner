package main.controller;

import main.dao.TestDao;
import main.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestDao testDao;
    @GetMapping
    public Iterable<Test> test(){
        Test test = new Test();
        test.setTest("test");
        testDao.save(test);

        return testDao.findAll();
    }
}
