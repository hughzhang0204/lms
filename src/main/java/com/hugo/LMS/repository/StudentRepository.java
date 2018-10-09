package com.hugo.LMS.repository;

import com.hugo.LMS.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
