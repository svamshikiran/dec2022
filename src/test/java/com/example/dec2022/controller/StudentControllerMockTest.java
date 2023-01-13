package com.example.dec2022.controller;

import com.example.dec2022.model.Student;
import com.example.dec2022.service.StudentService;
import org.apache.coyote.Response;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StudentControllerMockTest {

    @InjectMocks
    StudentController studentController;

    @Mock
    StudentService studentService;

    private static Student student;
    private static List<Student> students;

    @Before
    public void doSetup(){
        students = new ArrayList<>();
        student = new Student();
        student.setRollno(1);
        student.setName("test");
        student.setCity("testCity");
        students.add(student);
    }

    @Test
    public void testGetAllStudents(){
        when(studentService.getAllStudents()).thenReturn(students);
        List<Student> actualStudents = studentController.getAllStudents();
        assertEquals(1, actualStudents.size());
        assertEquals("test", students.get(0).getName());
    }

    @Test
    public void testGetStudentById(){
        when(studentService.getStudentByRollno(anyInt())).thenReturn(student);
        ResponseEntity<Object> response = studentController.getStudentById(1);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetStudentByIdException(){
        when(studentService.getStudentByRollno(anyInt())).thenReturn(new Student());
        ResponseEntity<Object> response = studentController.getStudentById(1);
        assertEquals(400, response.getStatusCodeValue());
    }

}
