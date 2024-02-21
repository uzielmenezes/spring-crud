package com.uziel.springcrud.controller;

import java.util.List;

import com.uziel.springcrud.model.Course;
import com.uziel.springcrud.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Course> listAllCourses() {
        return courseRepository.findAll();
    }

    // PathVariable can pass the identifier in case the parameter doesn't match name
    @SuppressWarnings("null")
    @GetMapping("/{id}")
    public ResponseEntity<Course> findCourseById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(recordValue -> ResponseEntity.ok().body(recordValue))
                .orElse(ResponseEntity.notFound().build());
    }

    // @RequestMapping(method = RequestMethod.POST)
    @SuppressWarnings("null")
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course createCourse(@RequestBody @Valid Course course) {
        return courseRepository.save(course);
    }

    @SuppressWarnings("null")
    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Course course) {

        return courseRepository.findById(id)
                .map(recordValue -> {
                    recordValue.setName(course.getName());
                    recordValue.setCategory(course.getCategory());
                    Course updateValue = courseRepository.save(recordValue);
                    return ResponseEntity.ok().body(updateValue);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @SuppressWarnings("null")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(recordValue -> {
                    courseRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
