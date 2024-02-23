package com.uziel.springcrud.mapper;

import org.springframework.stereotype.Component;

import com.uziel.springcrud.dto.CourseDTO;
import com.uziel.springcrud.model.Course;

import jakarta.validation.constraints.NotNull;

@Component
public class CourseMapper {

    public CourseDTO toDTO(@NotNull Course course) {
        return new CourseDTO(course.getId(), course.getName(), course.getCategory());
    }

    public Course toEntity(@NotNull CourseDTO courseDTO) {
        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(courseDTO.category());
        return course;
    }
}
