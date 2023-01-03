package com.example.dec2022.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Stating that this is a controller
@RequestMapping("/hello") //name of the controller
public class HelloController {

    @GetMapping("/greet")
    public String sayHello(){
        return "WELCOME TO THE SPRINGBOOT FRAMEWORK";
    }

    @GetMapping("/greet/{name}") //{} - indicates that request is coming with an input in the URL
    public String sayHello(@PathVariable("name") String inputName){
        return "HELLO "+inputName+ ", WELCOME TO THE SPRINGBOOT FRAMEWORK";
    }
}
