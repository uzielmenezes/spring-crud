package com.uziel.springcrud.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.uziel.springcrud.dto.CourseDTO;
import com.uziel.springcrud.dto.CoursePageDTO;
import com.uziel.springcrud.exception.RecordNotFoundException;
import com.uziel.springcrud.mapper.CourseMapper;
import com.uziel.springcrud.model.Course;
import com.uziel.springcrud.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public CoursePageDTO listAllCourses(@PositiveOrZero int page, @Positive @Max(100) int pageSize) {

        Page<Course> coursePage = courseRepository.findAll(PageRequest.of(page, pageSize));
        List<CourseDTO> courses = coursePage.get().map(courseMapper::toDTO).toList();
        return new CoursePageDTO(courses, coursePage.getTotalElements(), coursePage.getTotalPages());
    }

    public CourseDTO findCourseById(@NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO createCourse(@Valid @NotNull CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO);
        return courseMapper.toDTO(courseRepository.save(course));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO courseDTO) {
        Course selectedCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));

        Course course = courseMapper.toEntity(courseDTO);
        selectedCourse.setName(courseDTO.name());
        selectedCourse.setCategory(courseMapper.convertCategoryValue(courseDTO.category()));

        // cleaning lessons to add new ones from update
        selectedCourse.getLessons().clear();
        course.getLessons().forEach(lesson -> selectedCourse.getLessons().add(lesson));

        return courseMapper.toDTO(courseRepository.save(selectedCourse));
    }

    public void delete(@NotNull @Positive Long id) {
        courseRepository.findById(id)
                .ifPresentOrElse(
                        courseRepository::delete,
                        () -> {
                            throw new RecordNotFoundException(id);
                        });
    }
}
