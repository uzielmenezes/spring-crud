package com.uziel.springcrud.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    ACTIVE("Active"), INACTIVE("Inactive");

    private String value;
}
