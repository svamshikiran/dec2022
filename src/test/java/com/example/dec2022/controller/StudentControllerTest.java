package com.example.dec2022.controller;

import com.example.dec2022.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentControllerTest {

    @Autowired
    StudentController studentController;

    @Test
    public void testGetStudentByRollno(){

        ResponseEntity<Object> response = studentController.getStudentById(1);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1, ((Student)response.getBody()).getRollno());
        assertEquals("VAMSHI", ((Student)response.getBody()).getName());

    }

    @Test
    public void testGetStudentByRollnoException(){

        ResponseEntity<Object> response = studentController.getStudentById(100);
        assertEquals(400, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("STUDENT DOESN'T EXIST", response.getBody());

    }
}
