package ru.bolnik.springcourse.firstspringrestapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FirstRestController {


//    @ResponseBody
    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello World";
    }



}
