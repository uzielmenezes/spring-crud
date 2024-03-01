package com.uziel.springcrud.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.uziel.springcrud.dto.CourseDTO;
import com.uziel.springcrud.dto.LessonDTO;
import com.uziel.springcrud.enums.Category;
import com.uziel.springcrud.model.Course;

import jakarta.validation.constraints.NotNull;

@Component
public class CourseMapper {

    public CourseDTO toDTO(@NotNull Course course) {
        List<LessonDTO> lessons = course.getLessons()
                .stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
                .toList();

        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(),
                lessons);
    }

    public Course toEntity(@NotNull CourseDTO courseDTO) {
        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(this.convertCategoryValue(courseDTO.category()));
        return course;
    }

    public Category convertCategoryValue(@NotNull String value) {
        return switch (value) {
            case "Front-End" -> Category.FRONT_END;
            case "Back-End" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Invalid Category: " + value);
        };
    }
}
