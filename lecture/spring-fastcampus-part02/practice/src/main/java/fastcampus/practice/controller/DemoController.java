package fastcampus.practice.controller;

import fastcampus.practice.service.DemoService;
import fastcampus.practice.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    @Qualifier("demoService")
    private Service service;


    @GetMapping("/api/v1/test")
    public String test() {
        return service.getTest();
    }
}
