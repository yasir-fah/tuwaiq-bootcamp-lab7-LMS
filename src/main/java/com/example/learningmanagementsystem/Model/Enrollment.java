package com.example.learningmanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Enrollment {

    @NotEmpty(message = "id can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "id must contain letters and/or numbers only with no spaces")
    @Size(min = 1, max = 10, message = "id's length must be between 1 and 10")
    private String id;


    @NotEmpty(message = "studentId can't be empty")
    @Pattern(regexp = "^[0-9]+$", message = "studentId must be numeric only")
    private String studentId;


    @NotEmpty(message = "courseId can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "courseId must contain letters and/or numbers only with no spaces")
    private String courseId;


    @NotEmpty(message = "semester can't be empty")
    @Pattern(regexp = "^(fall|spring|summer)$", message = "semester must be either fall, spring, or summer")
    private String semester;


    @NotEmpty(message = "status can't be empty")
    @Pattern(regexp = "^(active|completed|dropped)$", message = "status must be active, completed, or dropped")
    private String status;


    @Min(value = 0, message = "totalPoints can't be negative")
    @Max(value = 100, message = "totalPoints can't exceed 100")
    private int totalPoints;
}
