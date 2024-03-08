package com.uziel.springcrud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.uziel.springcrud.enums.Category;
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
		return args -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName(("Angular"));
			c.setCategory(Category.FRONT_END);

			Lesson l = new Lesson();
			l.setName("Introdução");
			l.setYoutubeUrl("watch?=1111");
			l.setCourse(c);
			c.getLessons().add(l);

			Lesson l1 = new Lesson();
			l1.setName("Angular");
			l1.setYoutubeUrl("watch?=1112");
			l1.setCourse(c);
			c.getLessons().add(l1);

			courseRepository.save(c);
		};
	}
}
