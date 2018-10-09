package com.hugo.LMS.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Course")
@Table(name="Course")
@Access(AccessType.FIELD)
public class Course {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String bibliography;


    @ManyToMany(mappedBy="courses")
    private List<Lecturer> lecturers = new ArrayList<Lecturer>();

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<Student>();

    public Course() {
    }

    public Course(Long id, String name, String bibliography) {
        this.id = id;
        this.name = name;
        this.bibliography = bibliography;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBibliography() {
        return bibliography;
    }

    public void setBibliography(String bibliography) {
        this.bibliography = bibliography;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
