package com.uziel.springcrud.controller;

import java.util.List;

import com.uziel.springcrud.model.Course;
import com.uziel.springcrud.repository.CourseRepository;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/{id}")
    public ResponseEntity<Course> findCourseById(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(recordValue -> ResponseEntity.ok().body(recordValue))
                .orElse(ResponseEntity.notFound().build());
    }

    // @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
        // return
        // ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {

        return courseRepository.findById(id)
                .map(recordValue -> {
                    recordValue.setName(course.getName());
                    recordValue.setCategory(course.getCategory());
                    Course updateValue = courseRepository.save(recordValue);
                    return ResponseEntity.ok().body(updateValue);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
