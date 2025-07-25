package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Api.ApiResponse;
import com.example.learningmanagementsystem.Model.Enrollment;
import com.example.learningmanagementsystem.Service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllEnrollments() {
        return ResponseEntity.status(200).body(enrollmentService.getAllEnrollments());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEnrollment(@Valid @RequestBody Enrollment enrollment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        int add = enrollmentService.addEnrollment(enrollment);

        if (add == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("enrollment id already taken"));
        }
        if (add == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("student not found"));
        }
        if (add == 3) {
            return ResponseEntity.status(400).body(new ApiResponse("course not found"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("enrollment added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEnrollment(@PathVariable String id, @Valid @RequestBody Enrollment enrollment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        int update = enrollmentService.updateEnrollment(id, enrollment);

        if (update == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("enrollment id already taken"));
        }
        if (update == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("student not found"));
        }
        if (update == 3) {
            return ResponseEntity.status(400).body(new ApiResponse("course not found"));
        }
        if (update == 4) {
            return ResponseEntity.status(400).body(new ApiResponse("enrollment id not found"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("enrollment updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEnrollment(@PathVariable String id) {
        boolean isDeleted = enrollmentService.deleteEnrollment(id);

        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("enrollment deleted successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("enrollment id not found"));
    }




    // extra endpoint 1: get enrollments of a student
    @GetMapping("/get/student/{studentId}")
    public ResponseEntity<?> getEnrollmentsByStudent(@PathVariable String studentId) {
        ArrayList<Enrollment> result = enrollmentService.getEnrollmentsOfStudent(studentId);

        if (result == null) {
            return ResponseEntity.status(400).body(new ApiResponse("student id not found"));
        }

        return ResponseEntity.status(200).body(result);
    }




    // extra endpoint 2: get enrollments of a course
    @GetMapping("/get/course/{courseId}")
    public ResponseEntity<?> getEnrollmentsByCourse(@PathVariable String courseId) {
        ArrayList<Enrollment> result = enrollmentService.getEnrollmentsOfCourses(courseId);

        if (result == null) {
            return ResponseEntity.status(400).body(new ApiResponse("course id not found"));
        }

        return ResponseEntity.status(200).body(result);
    }




    // extra endpoint 3: update status of enrollment
    @PutMapping("/update/status/{id}")
    public ResponseEntity<?> updateEnrollmentStatus(@PathVariable String id, @RequestBody String status) {
        int result = enrollmentService.updateStatus(id, status);

        System.out.println(status);
        if (result == 1)
            return ResponseEntity.status(400).body(new ApiResponse("status already set to '" + status + "'"));
        if (result == 2)
            return ResponseEntity.status(200).body(new ApiResponse("status updated to '" + status + "'"));
        if (result == 3)
            return ResponseEntity.status(400).body(new ApiResponse("enrollment id not found"));
        if (result == 4)
            return ResponseEntity.status(400).body(new ApiResponse("invalid status — must be: active, completed, or dropped"));

        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }




    // extra endpoint 4: update total points
    @PutMapping("/update/points/{id}")
    public ResponseEntity<?> updateTotalPoints(@PathVariable String id, @RequestBody int points) {
        int result = enrollmentService.updatePoints(id, points);

        if (result == 1)
            return ResponseEntity.status(400).body(new ApiResponse("points already set to " + points));
        if (result == 2)
            return ResponseEntity.status(200).body(new ApiResponse("points updated to " + points));
        if (result == 3)
            return ResponseEntity.status(400).body(new ApiResponse("enrollment id not found"));
        if (result == 4)
            return ResponseEntity.status(400).body(new ApiResponse("invalid points — must be between 0 and 100"));

        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }



}
