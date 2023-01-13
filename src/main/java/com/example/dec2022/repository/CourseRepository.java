package com.example.dec2022.repository;

import com.example.dec2022.model.Courses;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends MongoRepository<Courses, Integer> {
}
