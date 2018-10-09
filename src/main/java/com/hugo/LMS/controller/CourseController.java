package com.hugo.LMS.controller;


import com.hugo.LMS.entity.Course;
import com.hugo.LMS.entity.CourseDTO;
import com.hugo.LMS.repository.CourseRepository;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(tags="Courses")
public class CourseController implements BaseController<Course, CourseDTO>{

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(value = "/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find all courses")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Success", response =  Course.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> list = courseRepository.findAll();

        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<CourseDTO> dtos = list.stream().map(course -> getDTO(course)).collect(Collectors.toList());

        return new ResponseEntity(dtos, HttpStatus.OK);
    }

    @GetMapping("/courses/{id}")
    @ApiOperation(value = "Find course by id")
    public ResponseEntity<Object> getCourseById(@PathVariable long id){

        Optional<Course> optional = courseRepository.findById(id);

        if(!optional.isPresent()){
            return  ResponseEntity.notFound().build();
//            return ResponseEntity.ok().build();
        }

        Course course = optional.get();
        return new ResponseEntity(getDTO(course), HttpStatus.OK);
    }

    @PostMapping("/courses")
    @ApiOperation(value = "Create a new course")
    public ResponseEntity<Object> createCourse(@RequestBody CourseDTO dto){

        Course course = getObject(dto);
        course = courseRepository.saveAndFlush(course);
        return new ResponseEntity(getDTO(course), HttpStatus.CREATED);
    }

    @PutMapping("/courses/{id}")
    @ApiOperation(value = "update a course detail")
    public ResponseEntity<Object> updateCourse(@PathVariable long id, @RequestBody CourseDTO dto){
        Optional<Course> optional = courseRepository.findById(id);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Course course = optional.get();
        course.setName(dto.getName());
        course.setBibliography(dto.getBibliography());

        course = courseRepository.saveAndFlush(course);
        return new ResponseEntity(getDTO(course), HttpStatus.OK);
    }

    @DeleteMapping("/courses/{id}")
    @ApiOperation(value = "Delete a course")
    public ResponseEntity<Object> deleteCourse(@PathVariable long id){
        Optional<Course> optional = courseRepository.findById(id);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        courseRepository.deleteById(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public CourseDTO getDTO(Course entity) {

        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBibliography(entity.getBibliography());

        return dto;
    }

    @Override
    public Course getObject(CourseDTO dto) {

        Course course = new Course();
        course.setName(dto.getName());
        course.setBibliography(dto.getBibliography());
        return  course;
    }
}
