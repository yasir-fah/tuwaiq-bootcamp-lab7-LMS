package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Api.ApiResponse;
import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.status(200).body(courseService.getAllCourses());
    }




    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@Valid @RequestBody Course course, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        int add = courseService.addCourse(course);

        if (add == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("course id already taken"));
        }
        if (add == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("instructor not found"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("course added successfully"));
    }




    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable String id, @Valid @RequestBody Course course, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        int update = courseService.updateCourse(id, course);

        if (update == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("course id already taken"));
        }
        if (update == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("instructor not found"));
        }
        if (update == 3) {
            return ResponseEntity.status(400).body(new ApiResponse("course id not found"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("course updated successfully"));
    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable String id) {
        boolean isDeleted = courseService.deleteCourse(id);

        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("course deleted successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("course id not found"));
    }



    // extra endpoint 1: get courses by instructorId
    @GetMapping("/get/instructor/{instructorId}")
    public ResponseEntity<?> getCoursesByInstructor(@PathVariable String instructorId) {
        return ResponseEntity.status(200).body(courseService.getCoursesByInstructor(instructorId));
    }




    // extra endpoint 2: get available courses (capacity > 0)
    @GetMapping("/get/available")
    public ResponseEntity<?> getAvailableCourses() {
        return ResponseEntity.status(200).body(courseService.getAvailable());
    }




    // extra endpoint 3: increase capacity of a course
    @PutMapping("/update/increase/capacity/{id}")
    public ResponseEntity<?> increaseCourseCapacity(@PathVariable String id) {
        int result = courseService.increaseCapacity(id);

        if (result == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("course capacity is already full"));
        }
        if (result == 2) {
            return ResponseEntity.status(200).body(new ApiResponse("course capacity increased by 1"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("course id not found"));
    }




    //  extra endpoint 4: assign instructor to course
    @PutMapping("/update/assign/{instructorid}/{courseId}")
    public ResponseEntity<?> assignInstructor(@PathVariable String courseId, @PathVariable String instructorid) {

        int result = courseService.assignInstructor(courseId, instructorid);

        if (result == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("instructor not found"));
        }
        if (result == 2) {
            return ResponseEntity.status(200).body(new ApiResponse("instructor assigned to course successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("course not found"));
    }





}
