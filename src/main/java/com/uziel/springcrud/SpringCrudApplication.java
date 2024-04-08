package com.uziel.springcrud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.uziel.springcrud.enums.Category;
import com.uziel.springcrud.enums.Status;
import com.uziel.springcrud.model.Course;
import com.uziel.springcrud.model.Lesson;
import com.uziel.springcrud.repository.CourseRepository;

@SpringBootApplication
public class SpringCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> extracted(courseRepository);
	}

	private void extracted(CourseRepository courseRepository) {
		courseRepository.deleteAll();
		for (int i = 1; i < 5; i++) {
			Course c = new Course();
			c.setName("Course " + i);
			c.setCategory(Category.FRONT_END);
			c.setStatus(Status.ACTIVE);

			for (int j = 1; j < 10; j++) {
				Lesson lesson = new Lesson();
				lesson.setName("Lesson " + j);
				lesson.setYoutubeUrl("Fj3Zvf-N4bk");
				c.addLesson(lesson);
			}

			courseRepository.save(c);
		}
	}
}
