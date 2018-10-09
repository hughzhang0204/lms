package com.hugo.LMS.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Lecturer")
@Table(name="Lecturer")
@Access(AccessType.FIELD)
public class Lecturer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String language;

    @Column
    private Integer maximumStudent;

    @Column
    private double fee;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="course_lecturer",
            joinColumns = @JoinColumn(name = "lecturer_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<Course>();

    public Lecturer() {
    }

    public Lecturer(Long id, String title, String description, String language, Integer maximumStudent, double fee) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.language = language;
        this.maximumStudent = maximumStudent;
        this.fee = fee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getMaximumStudent() {
        return maximumStudent;
    }

    public void setMaximumStudent(Integer maximumStudent) {
        this.maximumStudent = maximumStudent;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
