package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Model.Instructor;
import com.example.learningmanagementsystem.Model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Currency;

@Service
@RequiredArgsConstructor // for injection
public class CourseService {

    ArrayList<Course> courses = new ArrayList<>();

    private final InstructorService instructorService;



    public ArrayList<Course> getAllCourses() {
        return courses;
    }



    //check if id is unique and instructor is matched or 'none'
    public int addCourse(Course course) {
        for (Course c : courses) {
            if (c.getId().equals(course.getId())) {
                return 1; // id already taken
            }
        }

        String instructorId = course.getInstructorId();

        if (!instructorId.equalsIgnoreCase("none") &&
                !instructorService.isInstructorExists(instructorId)) {
            return 2; // instructor not found
        }

        courses.add(course);
        return 3; // course added successfully
    }



    // update also check course id is unique and instructor exist:
    public int updateCourse(String id, Course course){
        for(Course c : courses) {
            if(c.getId().equals(course.getId())){
                return 1; // id already taken
            }
        }

        String instructorId = course.getInstructorId();
        if(!instructorId.equalsIgnoreCase("none") &&
                !instructorService.isInstructorExists(instructorId)){
            return 2; // not found
        }

        for (Course c : courses){
            if(c.getId().equals(id)){
                courses.set(courses.indexOf(c), course);
                return 4; // course updated
            }
        }

        return 3; // id not found

    }


    public boolean deleteCourse(String id){
        for (Course c : courses){
            if(c.getId().equals(id)){
                courses.remove(courses.indexOf(c));
                return true;
            }
        }
        return false;
    }



    // check if the course exist to assign it in enrollment or not:
    public boolean isCourseExist(String id) {
        for (Course course : courses) {
            if(course.getId().equals(id)){
                course.setCapacity(course.getCapacity() - 1); // the capacity increase with each enrollment added to the system.
                return true; // course exist
            }
        }
        return false;
    }





    // extra end-point 1:
    public ArrayList<Course> getCoursesByInstructor(String instructorId) {
        ArrayList<Course> result = new ArrayList<>();
        for (Course course : courses) {
            if (course.getInstructorId().equals(instructorId)) {
                result.add(course);
            }
        }
        // courses from one instructor:
        return result;
    }


    // extra end-point 2:
    public ArrayList<Course> getAvailable(){
        ArrayList<Course>  availableList = new ArrayList<>();

        for (Course c : courses) {
            if(c.getCapacity() > 0){
                availableList.add(c);
            }
        }
        return availableList;
    }



    // extra end-point 3:
    public int increaseCapacity(String id){
        for (Course c : courses){
            if(c.getId().equals(id)){
                if(c.getCapacity() >= 30){
                    return 1; // capacity is full
                }
                c.setCapacity(c.getCapacity() + 1);
                return 2; // capacity++
            }
        }
        return 3; // id not found
    }


    // extra end-point 4:
    public int assignInstructor(String courseId, String instructorId) {
        if (!instructorService.isInstructorExists(instructorId)) {
            return 1; // instructor not found
        }

        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                course.setInstructorId(instructorId);
                return 2; // instructor assigned successfully
            }
        }

        return 3; // course not found
    }



}
