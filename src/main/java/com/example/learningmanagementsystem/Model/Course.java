package com.example.learningmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Course {

    @NotEmpty(message = "id can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "id must contain letters and/or numbers only with no spaces")
    @Size(min = 1, max = 10, message = "id's length must be between 1 and 10")
    private String id;

    @NotEmpty(message = "title can't be empty")
    @Size(max = 30, message = "title's max length is 30")
    private String title;

    @NotEmpty(message = "description can't be empty")
    @Size(max = 100, message = "description's max length is 100")
    private String description;

    @NotEmpty(message = "instructorId can't be empty")
    @Pattern(regexp = "^([0-9]+|none)$", message = "instructorId must be an exist instructor or 'none' ")
    private String instructorId;

    @NotNull(message = "start date can't be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull(message = "end date can't be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "end date can't be in the past")
    private Date endDate;

    @Min(value = 10, message = "capacity must be at least 10") // students
    @Max(value = 30, message = "capacity must be at most 30") // students
    private int capacity;

}
