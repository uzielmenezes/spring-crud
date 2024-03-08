package com.uziel.springcrud.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.uziel.springcrud.enums.Category;
import com.uziel.springcrud.enums.validation.ValueOfEnum;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CourseDTO(
                Long id,
                @NotBlank @NotNull @Length(min = 3, max = 50) String name,
                @NotBlank @NotNull @Length(max = 10) @ValueOfEnum(enumClass = Category.class) String category,
                @NotNull @NotEmpty @Valid List<LessonDTO> lessons) {

}
