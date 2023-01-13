package com.example.dec2022.controller;

import com.example.dec2022.model.Courses;
import com.example.dec2022.service.CoursesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    @Autowired
    CoursesService service;

    @GetMapping("/all")
    public List<Courses> getAllCourses(){
        return service.getAllCourses();
    }

    @Operation(description = "THIS IS FOR ADDING THE COURSES")
    @ApiResponse(responseCode = "200", description = "THIS IS SUCCESSFUL RESPONSE")
    @PostMapping("/add")
    public void addCourses(@RequestBody Courses course){
        service.addCourse(course);
    }

    @DeleteMapping("/delete/{courseid}")
    public void deleteCourse(@PathVariable("courseid") int courseid){
        service.deleteCourse(courseid);
    }

    @PutMapping("/update")
    public void updateCourse(@RequestBody Courses course){
        service.updateCourse(course);
    }
}
