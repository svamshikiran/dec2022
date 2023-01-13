package com.example.dec2022.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HelloControllerTest {

    @Autowired
    HelloController helloController;

    @Test
    public void testSayHello(){

        String expectedString = "WELCOME TO THE SPRINGBOOT FRAMEWORK";

        String actualString = helloController.sayHello();

        assertEquals(expectedString, actualString);

    }

    @Test
    public void testSayHelloWithParameter(){
        String inputName = "VAMSHI";
        String expectedString = "HELLO "+inputName+ ", WELCOME TO THE SPRINGBOOT FRAMEWORK";

        String actualString = helloController.sayHello(inputName);

        assertEquals(expectedString, actualString);
    }
}
