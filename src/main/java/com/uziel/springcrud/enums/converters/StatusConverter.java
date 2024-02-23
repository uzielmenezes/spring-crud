package com.uziel.springcrud.enums.converters;

import com.uziel.springcrud.enums.Status;

import java.util.stream.Stream;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jakarta.validation.constraints.NotNull;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(@NotNull Status status) {
        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(@NotNull String value) {
        return Stream.of(Status.values())
                .filter(s -> s.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
