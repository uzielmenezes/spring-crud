package com.uziel.springcrud.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.uziel.springcrud.exception.RecordNotFoundException;
import com.uziel.springcrud.model.Course;
import com.uziel.springcrud.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.NonNull;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> listAllCourses() {
        return courseRepository.findAll();
    }

    public Course findCourseById(@NonNull @NotNull @Positive Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Course createCourse(@Valid @NonNull Course course) {
        return courseRepository.save(course);
    }

    public Course update(@NonNull @NotNull @Positive Long id, @Valid Course course) {
        Course selectedCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));

        selectedCourse.setName(course.getName());
        selectedCourse.setCategory(course.getCategory());

        return courseRepository.save(selectedCourse);
    }

    public void delete(@NonNull @NotNull @Positive Long id) {
        courseRepository.findById(id)
                .ifPresentOrElse(
                        courseRepository::delete,
                        () -> {
                            throw new RecordNotFoundException(id);
                        });
    }
}
