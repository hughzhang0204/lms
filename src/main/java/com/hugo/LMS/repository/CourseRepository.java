package com.hugo.LMS.repository;

import com.hugo.LMS.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
