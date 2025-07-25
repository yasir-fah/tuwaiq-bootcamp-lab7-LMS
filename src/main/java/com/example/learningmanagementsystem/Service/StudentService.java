package com.example.learningmanagementsystem.Service;


import com.example.learningmanagementsystem.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {

    ArrayList<Student> students = new ArrayList<>();


    public ArrayList<Student> getAllStudents() {
        return students;
    }


    public int addStudent(Student student) {
        // check if id or email is redundant:
        for (Student st : students){
            if(st.getId().equals(student.getId())){
                return 1; // id taken
            }
            if(st.getEmail().equalsIgnoreCase(student.getEmail())){
                return 2; // email taken
            }
        }


        students.add(student);
        return 3; // if unique:
    }


    public int updateStudent(String id, Student student){

        for (Student st : students){
            if(st.getId().equals(student.getId())){
                return 1; // id already taken
            }
            if(st.getEmail().equalsIgnoreCase(student.getEmail())){
                return 2; // email is already taken
            }
        }

        for (Student st : students) {
            if(st.getId().equals(id)){
                students.set(students.indexOf(st), student);
                return 4; // id found successfully
            }
        }

        return 3; // id not found

    }


    public boolean deleteStudent(String id){

        for (Student st : students) {
            if(st.getId().equals(id)){
                students.remove(students.indexOf(st));
                return true; // deleted
            }
        }

        return false; // id not found

    }



    // check if user exist to assign him in enrollment or not:
    public boolean isStudentExist(String id) {
        for (Student student : students) {
            if(student.getId().equals(id)){
                return true; // student exist
            }
        }
        return false;
    }



    // for extra end-point 1:
    public ArrayList<Student> getByStatus(String status){
        ArrayList<Student> studentList = new ArrayList<>();

        for (Student st : students) {
            if(st.getStatus().equalsIgnoreCase(status)){
                studentList.add(st);
            }
        }
        return studentList;
    }


    // for extra end-point 2:
    public ArrayList<Student> getStudentsByGPA(double gpa) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getGPA() >= gpa) {
                result.add(student);
            }
        }
        return result;
    }


    // for extra end-point 3:
    public ArrayList<Student> getStudentsByAge(int age) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getAge() >= age) {
                result.add(student);
            }
        }
        return result;
    }


    // extra end-point 4 to graduated:
    public int updateStatus(String id) {
       for (Student student : students){
           if(student.getId().equals(id) && student.getStatus().equalsIgnoreCase("graduated")){
               return 1; // student already graduated.
           }
           if(student.getId().equals(id) && !student.getStatus().equalsIgnoreCase("graduated")){
               student.setStatus("graduated");
               return 2; // change status
           }
       }

       return 3; // id not found

    }









}
