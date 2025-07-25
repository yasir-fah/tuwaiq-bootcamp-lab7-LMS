package com.example.learningmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Instructor {

    @NotEmpty(message = "id can't be empty")
    @Pattern(regexp = "^[0-9]+$", message = "id contains numbers only")
    @Size(min = 1, max = 10, message = "id's length must be from 1 to 10")
    private String id;

    @NotEmpty(message = "first name can't be empty")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name must contain letters only, and no spaces")
    @Size(max = 15, message = "name's max length is 15")
    private String firstName;

    @NotEmpty(message = "last name can't be empty")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name must contain letters only, and no spaces")
    @Size(max = 15, message = "name's max length is 15")
    private String lastName;

    @Email(message = "email should be valid")
    @NotEmpty(message = "email can't be empty")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "hire date can't be in the future")
    private LocalDate hireDate;

    @NotEmpty(message = "department can't be empty")
    private String department;

    @NotEmpty(message = "status can't be empty")
    @Pattern(regexp = "^(active|retired)$", message = "status must be active or retired")
    private String status;



}
