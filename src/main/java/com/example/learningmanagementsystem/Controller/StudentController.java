package com.example.learningmanagementsystem.Controller;


import com.example.learningmanagementsystem.Api.ApiResponse;
import com.example.learningmanagementsystem.Model.Student;
import com.example.learningmanagementsystem.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping("/get")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }



    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student student, Errors errors) {

        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        int add = studentService.addStudent(student);

        if(add == 1){
            return ResponseEntity.status(400).body(new ApiResponse("id is taken before"));
        }
        if(add == 2){
            return ResponseEntity.status(400).body(new ApiResponse("email is taken before"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("student added successfully"));

    }




    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id,@Valid @RequestBody Student student, Errors errors) {

        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }


        int update = studentService.updateStudent(id,student);

        if(update == 1){
            return ResponseEntity.status(400).body(new ApiResponse("student id already taken"));
        }
        if(update == 2){
            return ResponseEntity.status(400).body(new ApiResponse("student email already taken"));
        }
        if(update == 3){
            return ResponseEntity.status(400).body(new ApiResponse("id not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("student updated successfully"));

    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id) {

        boolean isDelete = studentService.deleteStudent(id);

        if(isDelete){
            return ResponseEntity.status(200).body(new ApiResponse("student deleted successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("student id not found"));

    }




    // extra end-point 1:
    @GetMapping("/get/status/{status}")
    public ResponseEntity<?> getByStatus(@PathVariable String status) {
        ArrayList<Student> studentList = studentService.getByStatus(status);
        return ResponseEntity.status(200).body(studentList);
    }



    // extra end-point 2:
    @GetMapping("/get/gpa/{gpa}")
    public ResponseEntity<?> getStudentsByGPA(@PathVariable double gpa) {
        ArrayList<Student> studentList = studentService.getStudentsByGPA(gpa);
        return ResponseEntity.status(200).body(studentList);
    }



    // extra end-point 3:
    @GetMapping("/get/age/{age}")
    public ResponseEntity<?> getStudentsByAge(@PathVariable int age) {
        ArrayList<Student> studentList = studentService.getStudentsByAge(age);
        return ResponseEntity.status(200).body(studentList);
    }




    // extra end-point 4 change status to graduated:
    @PutMapping("/update/status/{id}")
    public ResponseEntity<?> updateStudentStatus(@PathVariable String id) {

        int changing = studentService.updateStatus(id);

        if(changing == 1){
            return ResponseEntity.status(200).body(new ApiResponse("student is graduated already"));
        }
        if(changing == 2){
            return ResponseEntity.status(200).body(new ApiResponse("student status updated successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("id not found"));

    }












}

