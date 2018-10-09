package com.hugo.LMS.controller;

import com.hugo.LMS.entity.Course;
import com.hugo.LMS.entity.CourseDTO;
import com.hugo.LMS.entity.Student;
import com.hugo.LMS.entity.studentDTO;
import com.hugo.LMS.repository.StudentRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = "Students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find all students")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Success", response =  Student.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> list = studentRepository.findAll();

        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    @ApiOperation(value = "Find student by id")
    public ResponseEntity<Object> getStudentById(@PathVariable long id){

        Optional<Student> optional = studentRepository.findById(id);

        if(!optional.isPresent()){
            return  ResponseEntity.notFound().build();
        }

        Student student = optional.get();
        return new ResponseEntity(student, HttpStatus.OK);
    }

    @PostMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a new student")
    public ResponseEntity<Object> createCourse(@RequestBody studentDTO dto){

        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setDOB(dto.getDOB());
        student.setGender(dto.getGender());
        student.setEmail(dto.getEmail());
        student.setCredit(dto.getCredit());
        student = studentRepository.saveAndFlush(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/students/{id}")
    @ApiOperation(value = "update a student detail")
    public ResponseEntity<Object> updateStudent(@PathVariable long id, @RequestBody studentDTO dto){
        Optional<Student> optional = studentRepository.findById(id);

        if(!optional.isPresent()){
            return  ResponseEntity.notFound().build();
        }

        Student student = optional.get();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setDOB(dto.getDOB());
        student.setGender(dto.getGender());
        student.setEmail(dto.getEmail());
        student.setCredit(dto.getCredit());
        student = studentRepository.saveAndFlush(student);
        student = studentRepository.saveAndFlush(student);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    @ApiOperation(value = "Delete a student")
    public ResponseEntity<Object> deleteStudent(@PathVariable long id){
        Optional<Student> optional = studentRepository.findById(id);

        if(!optional.isPresent()){
            return  ResponseEntity.notFound().build();
        }
        studentRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
