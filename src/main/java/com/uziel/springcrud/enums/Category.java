package com.uziel.springcrud.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    BACK_END("Back-End"), FRONT_END("Front-End");

    private String value;

    @Override
    public String toString() {
        return value;
    }

}
