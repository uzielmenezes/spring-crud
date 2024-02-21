package com.uziel.springcrud.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inactive' WHERE id = ?")
@SQLRestriction("status = 'Active'")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 3, max = 50)
    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @NotBlank
    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "Back-End|Front-End")
    @Column(length = 10, nullable = false)
    private String category;

    @NotBlank
    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "Active|Inactive")
    @Column(length = 10, nullable = false)
    private String status = "Active";
}
