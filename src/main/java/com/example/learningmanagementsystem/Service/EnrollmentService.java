package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Enrollment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    ArrayList<Enrollment> enrollments = new ArrayList<>();

    private final StudentService studentService;
    private final CourseService courseService;

    public ArrayList<Enrollment> getAllEnrollments() {
        return enrollments;
    }

    // Add enrollment
    public int addEnrollment(Enrollment enrollment) {
        for (Enrollment e : enrollments) {
            if (e.getId().equals(enrollment.getId())) {
                return 1; // id already taken
            }
        }

        // Check if student and course exist
        if (!studentService.isStudentExist(enrollment.getStudentId())) {
            return 2; // student not found
        }

        if (!courseService.isCourseExist(enrollment.getCourseId())) {
            return 3; // course not found
        }


        enrollments.add(enrollment);
        return 4; // added successfully
    }

    // Update enrollment
    public int updateEnrollment(String id, Enrollment updatedEnrollment) {
        for (Enrollment e : enrollments) {
            if (e.getId().equals(updatedEnrollment.getId())) {
                return 1; // new id is already taken
            }
        }

        if (!studentService.isStudentExist(updatedEnrollment.getStudentId())) {
            return 2; // student not found
        }

        if (!courseService.isCourseExist(updatedEnrollment.getCourseId())) {
            return 3; // course not found
        }

        for (Enrollment e : enrollments) {
            if (e.getId().equals(id)) {
                enrollments.set(enrollments.indexOf(e), updatedEnrollment);
                return 5; // updated successfully
            }
        }

        return 4; // enrollment id not found
    }

    // Delete enrollment
    public boolean deleteEnrollment(String id) {
        for (Enrollment e : enrollments) {
            if (e.getId().equals(id)) {
                enrollments.remove(e);
                return true;
            }
        }
        return false;
    }




    // extra end-point 1:
    public ArrayList<Enrollment> getEnrollmentsOfStudent(String studentId) {

        ArrayList<Enrollment> commonStudents = new ArrayList<>();

        // check if student exist by inject (Student service) at (Enrollment service)
        if(!studentService.isStudentExist(studentId)){
            return null; // id not found
        }

        for (Enrollment en : enrollments ){
            if(en.getStudentId().equals(studentId)){
                commonStudents.add(en);
            }
        }
        return commonStudents;

    }


    //extra end-point 2:
    public ArrayList<Enrollment> getEnrollmentsOfCourses(String courseId){

        ArrayList<Enrollment> commonCourses = new ArrayList<>();
        if(!courseService.isCourseExist(courseId)){
            return null ; // id not found
        }

        for (Enrollment en : enrollments ){
            if(en.getCourseId().equals(courseId)){
                commonCourses.add(en);
            }
        }
        return commonCourses;
    }



    // extra end-point 3:
    public int updateStatus(String id, String status) {
        String normalized = status.trim().toLowerCase();

        if (!normalized.equals("active") && !normalized.equals("completed") && !normalized.equals("dropped")) {
            return 4; // invalid status
        }

        for (Enrollment e : enrollments) {
            if (e.getId().equals(id)) {
                if (e.getStatus().equalsIgnoreCase(normalized)) {
                    return 1; // status already same
                }
                e.setStatus(normalized);
                return 2; // updated
            }
        }

        return 3; // id not found
    }



    // extra end-point 4:
    public int updatePoints(String id, int points) {
        if (points < 0 || points > 100) {
            return 4; // invalid value
        }

        for (Enrollment e : enrollments) {
            if (e.getId().equals(id)) {
                if (e.getTotalPoints() == points) {
                    return 1; // already same
                }
                e.setTotalPoints(points);
                return 2; // updated
            }
        }

        return 3; // id not found
    }





}
