package com.hugo.LMS.controller;

import com.hugo.LMS.entity.Course;
import com.hugo.LMS.entity.Lecturer;
import com.hugo.LMS.entity.LecturerDTO;
import com.hugo.LMS.repository.CourseRepository;
import com.hugo.LMS.repository.LecturerRepository;
import io.swagger.annotations.*;
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
@Api(tags="Lecturers")
public class LecturerController implements BaseController<Lecturer, LecturerDTO>{

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private CourseRepository courseRepository;


    @GetMapping(value = "/lecturers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find all lecturers")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Success", response =  Lecturer.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    public ResponseEntity<List<LecturerDTO>> getAllLecturers(){
        List<Lecturer> list = lecturerRepository.findAll();

        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<LecturerDTO> dtos = list.stream()
                .map(lecture -> getDTO(lecture))
                .collect(Collectors.toList());

        return new ResponseEntity(dtos, HttpStatus.OK);
    }

    @GetMapping("/lecturers/{id}")
    @ApiOperation(value = "Find lecturer by id")
    public ResponseEntity<Object> getLecturerById(@PathVariable long id){

        Optional<Lecturer> optional = lecturerRepository.findById(id);

        if(!optional.isPresent()){
            return  ResponseEntity.notFound().build();
        }

        Lecturer lecturer = optional.get();
        return new ResponseEntity(getDTO(lecturer), HttpStatus.OK);
    }

    @PostMapping("/lecturers")
    @ApiOperation(value = "Create a new lecturer")
    public ResponseEntity<Object> createLecturer(@RequestBody LecturerDTO dto){

        Lecturer lecturer = getObject(dto);
        lecturerRepository.saveAndFlush(lecturer);

//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(savedStudent.getId()).toUri();

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/lecturers/{id}")
    @ApiOperation(value = "update a lecturer detail")
    public ResponseEntity<Object> updateLecturer(@PathVariable long id, @RequestBody Lecturer dto){
        Optional<Lecturer> optional = lecturerRepository.findById(id);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Lecturer lecturer = optional.get();

        lecturer.setDescription(dto.getDescription());
        lecturer.setFee(dto.getFee());
        lecturer.setLanguage(dto.getLanguage());
        lecturer.setMaximumStudent(dto.getMaximumStudent());
        lecturer.setTitle(dto.getTitle());

        lecturer = lecturerRepository.saveAndFlush(lecturer);

        return new ResponseEntity(getDTO(lecturer),HttpStatus.OK);
    }

    @DeleteMapping("/lecturers/{id}")
    @ApiOperation(value = "Delete a lecturer")
    public ResponseEntity<Object> deleteLecturer(@PathVariable long id){

        Optional<Lecturer> optional = lecturerRepository.findById(id);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        lecturerRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/lecturers/{lecturerId}/addtocourses/{courseId}")
    @ApiOperation("Add course to lecturer")
    public ResponseEntity<Object> addToCourse(@PathVariable long lecturerId, @PathVariable long courseId){

        Optional<Lecturer> optional = lecturerRepository.findById(lecturerId);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Optional<Course> optional1 =  courseRepository.findById(courseId);
        if(!optional1.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Lecturer lecturer = optional.get();
        Course course = optional1.get();
        lecturer.getCourses().add(course);
        lecturerRepository.saveAndFlush(lecturer);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Override
    public LecturerDTO getDTO(Lecturer entity){

        LecturerDTO dto = new LecturerDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setFee(entity.getFee());
        dto.setLanguage(entity.getLanguage());
        dto.setMaximumStudent(entity.getMaximumStudent());
        dto.setTitle(entity.getTitle());

        return dto;
    }

    @Override
    public Lecturer getObject(LecturerDTO dto) {

        Lecturer lecturer = new Lecturer();
        lecturer.setDescription(dto.getDescription());
        lecturer.setFee(dto.getFee());
        lecturer.setLanguage(dto.getLanguage());
        lecturer.setMaximumStudent(dto.getMaximumStudent());
        lecturer.setTitle(dto.getTitle());

        return lecturer;
    }
}
