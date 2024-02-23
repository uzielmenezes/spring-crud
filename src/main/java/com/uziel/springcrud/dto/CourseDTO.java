package com.uziel.springcrud.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CourseDTO(
        Long id,
        @NotBlank @NotNull @Length(min = 3, max = 50) String name,
        @NotBlank @NotNull @Length(max = 10) @Pattern(regexp = "Back-End|Front-End") String category) {

}
