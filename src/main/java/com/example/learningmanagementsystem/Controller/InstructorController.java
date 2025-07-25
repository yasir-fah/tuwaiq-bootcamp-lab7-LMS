package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Api.ApiResponse;
import com.example.learningmanagementsystem.Model.Instructor;
import com.example.learningmanagementsystem.Service.InstructorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/instructor")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllInstructors() {
        return ResponseEntity.status(200).body(instructorService.getAllInstructors());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addInstructor(@Valid @RequestBody Instructor instructor, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        int add = instructorService.addInstructor(instructor);

        if (add == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("id is taken before"));
        }
        if (add == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("email is taken before"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("instructor added successfully"));
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateInstructor(@PathVariable String id, @Valid @RequestBody Instructor instructor, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        int update = instructorService.updateInstructor(id, instructor);

        if (update == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("instructor id already taken"));
        }
        if (update == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("instructor email already taken"));
        }
        if (update == 3) {
            return ResponseEntity.status(400).body(new ApiResponse("instructor id not found"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("instructor updated successfully"));
    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInstructor(@PathVariable String id) {
        boolean isDelete = instructorService.deleteInstructor(id);
        if (isDelete) {
            return ResponseEntity.status(200).body(new ApiResponse("instructor deleted successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("instructor id not found"));
    }




    // extra endpoint 1: get by status
    @GetMapping("/get/status/{status}")
    public ResponseEntity<?> getInstructorsByStatus(@PathVariable String status) {
        return ResponseEntity.status(200).body(instructorService.getByStatus(status));
    }




    //  extra endpoint 2: get by hire date (after specific date)
    @GetMapping("/get/hired/{date}")
    public ResponseEntity<?> getInstructorsByHireDate(@PathVariable @DateTimeFormat LocalDate date) {
        return ResponseEntity.status(200).body(instructorService.getByHireDate(date));
    }




    // extra endpoint 3: get by department
    @GetMapping("/get/department/{department}")
    public ResponseEntity<?> getInstructorsByDepartment(@PathVariable String department) {
        return ResponseEntity.status(200).body(instructorService.getByDepartment(department));
    }



    ///  extra end-point 4:
    // this end point filtering by partials, (show result with names start with 'a', and result get minimized be increasing letters)
    @GetMapping("get/search/{letters}")
    public ResponseEntity<?> searchInstructorByNamePartially(@PathVariable String letters) {
        return ResponseEntity.status(200).body(instructorService.getByNamePartially(letters));
    }




}
