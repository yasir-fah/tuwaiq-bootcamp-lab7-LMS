package com.example.learningmanagementsystem.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class Student {


    @NotEmpty(message = "id can't be empty")
    @Pattern(regexp = "^[0-9]+$", message = "id contains numbers only")
    @Size(min = 1, max = 10, message = "id's length from 1 to 10")
    private String id;


    @NotEmpty(message = "first name can't be empty")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name must contain letters only, and no spaces")
    @Size(max = 15, message = "name's max length is 10")
    private String firstName;


    @NotEmpty(message = "last name can't be empty")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name must contain letters only, and no spaces")
    @Size(max = 15, message = "name's max length is ")
    private String LastName;

    @NotNull(message = "age can't be null")
    @Min(value = 18 , message = "age should be 18 and above")
    private int age;



    @Email(message = "email should be valid")
    @NotEmpty(message = "email can't be empty")
    private String email;



    @NotNull(message = "GPA can't be empty")
    @Min(0)
    @Max(5)
    private double GPA;


    @NotEmpty(message = "status can't be empty")
    @Pattern(regexp = "^(under-graduated|graduated)+$", message = "status only be: under-graduated|graduated")
    private String status;

}
