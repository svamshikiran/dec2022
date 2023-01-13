package com.example.dec2022.controller;

import com.example.dec2022.model.Student;
import com.example.dec2022.service.StudentService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService service; //Dependency Injection

    @GetMapping("/all")
    public List<Student> getAllStudents(){
        return service.getAllStudents();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addStudent(@RequestBody Student student){
        if(student.getRollno() == 0)
            return new ResponseEntity<>("ROLL NO IS MANDATORY", HttpStatus.BAD_REQUEST);
        try {
            service.addStudent(student);
            return new ResponseEntity<>("STUDENT RECORD ADDED SUCCESSFULLY", HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity<>("ERROR OCCURRED, PLEASE CONTACT ADMINISTRATOR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByRollno/{rollno}")
    public ResponseEntity<Object> getStudentById(@PathVariable("rollno") int rollno){
        Student student = service.getStudentByRollno(rollno);
        if(student.getRollno() == 0)
            return new ResponseEntity<>("STUDENT DOESN'T EXIST", HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/getByName/{name}")
    public List<Student> getStudentByName(@PathVariable("name") String name){
        return service.getStudentByName(name);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload( @RequestParam("file") MultipartFile file ) {

        String fileName = file.getOriginalFilename();
        try {
            service.readFileContents(file.getInputStream());
            FileUtils.forceDelete(file.getResource().getFile());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }

    @RequestMapping(path = "/download")
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"students.csv\"");
        service.writeStudentsToCsv(servletResponse.getWriter());
    }
}
