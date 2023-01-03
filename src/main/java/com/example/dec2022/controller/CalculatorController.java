package com.example.dec2022.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class CalculatorController {

    @GetMapping("/add/{first}/{second}")
    public double add(@PathVariable("first") double firstNumber,
                      @PathVariable("second") double secondNumber){
        return firstNumber+secondNumber;
    }
}
