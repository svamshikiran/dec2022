package com.example.dec2022.service;

import com.example.dec2022.kafka.KafkaProducerService;
import com.example.dec2022.model.Student;
import com.example.dec2022.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository repo;

    @Autowired
    KafkaProducerService kafkaProducerService;
    public List<Student> getAllStudents(){
        return repo.findAll(); //select * from student;
    }

    public void addStudent(Student student)throws JsonProcessingException{

        //repo.save(student);
        kafkaProducerService.sendSimpleMessage(student);
    }

    public Student getStudentByRollno(int rollno){
        Optional<Student> opStudent = repo.findById(rollno);
        if(opStudent.isPresent())
            return repo.findById(rollno).get();
        else
            return new Student();
    }

    public List<Student> getStudentByName(String name){
        return repo.findStudentByName(name);
    }

    public void writeStudentsToCsv(Writer writer) {

        List<Student> students = repo.findAll();

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ROLLNO", "NAME", "CITY");
            for (Student student : students) { //Enhanced For Loop
                csvPrinter.printRecord(student.getRollno(), student.getName(), student.getCity());
            }
        } catch (IOException e) {
            System.out.println("EXPCEPTION RAISED");
        }
    }

    public void readFileContents(InputStream inputStream)throws Exception {
        CSVParser parser = new CSVParser(new InputStreamReader(inputStream), CSVFormat.DEFAULT);

        List<CSVRecord> records = parser.getRecords();

        for (CSVRecord record : records) {
            Student student = new Student();
            student.setRollno(Integer.parseInt(record.get(0)));
            student.setName(record.get(1));
            student.setCity(record.get(2));

            repo.save(student);
        }
    }

}


/*
@Service
@RestController
@Repository
@Bean
@Component
 */