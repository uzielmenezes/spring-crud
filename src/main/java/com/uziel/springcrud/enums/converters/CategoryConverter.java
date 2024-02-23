package com.uziel.springcrud.enums.converters;

import com.uziel.springcrud.enums.Category;

import java.util.stream.Stream;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jakarta.validation.constraints.NotNull;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

    @Override
    public String convertToDatabaseColumn(@NotNull Category category) {
        return category.getValue();
    }

    @Override
    public Category convertToEntityAttribute(@NotNull String value) {
        return Stream.of(Category.values())
                .filter(c -> c.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
