package com.uziel.springcrud.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.uziel.springcrud.dto.CourseDTO;
import com.uziel.springcrud.exception.RecordNotFoundException;
import com.uziel.springcrud.mapper.CourseMapper;
import com.uziel.springcrud.model.Course;
import com.uziel.springcrud.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> listAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .toList();
    }

    @SuppressWarnings("null")
    public CourseDTO findCourseById(@NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    @SuppressWarnings("null")
    public CourseDTO createCourse(@Valid @NotNull CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO);
        return courseMapper.toDTO(courseRepository.save(course));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO courseDTO) {
        @SuppressWarnings("null")
        Course selectedCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));

        selectedCourse.setName(courseDTO.name());
        selectedCourse.setCategory(courseMapper.convertCategoryValue(courseDTO.category()));

        return courseMapper.toDTO(courseRepository.save(selectedCourse));
    }

    @SuppressWarnings("null")
    public void delete(@NotNull @Positive Long id) {
        courseRepository.findById(id)
                .ifPresentOrElse(
                        courseRepository::delete,
                        () -> {
                            throw new RecordNotFoundException(id);
                        });
    }
}
