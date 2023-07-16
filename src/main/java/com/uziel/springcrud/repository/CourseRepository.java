package com.uziel.springcrud.repository;

import com.uziel.springcrud.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
