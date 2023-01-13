package com.example.dec2022.service;

import com.example.dec2022.model.Courses;
import com.example.dec2022.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesService {

    @Autowired
    CourseRepository repo;

    public void addCourse(Courses course){
        repo.save(course);
    }

    public List<Courses> getAllCourses(){
        return repo.findAll();
    }

    public void deleteCourse(int courseid){
        repo.deleteById(courseid);
    }

    public void updateCourse(Courses course){
        repo.save(course);
    }

}
