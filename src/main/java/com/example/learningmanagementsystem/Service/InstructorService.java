package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Instructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class InstructorService {

    ArrayList<Instructor> instructors = new ArrayList<>();

    public ArrayList<Instructor> getAllInstructors() {
        return instructors;
    }

    public int addInstructor(Instructor instructor) {
        // Check if id or email already exists
        for (Instructor ins : instructors) {
            if (ins.getId().equals(instructor.getId())) {
                return 1; // ID already taken
            }
            if (ins.getEmail().equalsIgnoreCase(instructor.getEmail())) {
                return 2; // Email already taken
            }
        }

        instructors.add(instructor);
        return 3; // Added successfully
    }




    public int updateInstructor(String id, Instructor instructor) {

        for (Instructor ins : instructors) {
            if (ins.getId().equals(instructor.getId())) {
                return 1; // id already taken
            }
            if (ins.getEmail().equalsIgnoreCase(instructor.getEmail())) {
                return 2; // email is already taken
            }
        }

        for (Instructor ins : instructors) {
            if (ins.getId().equals(id)) {
                instructors.set(instructors.indexOf(ins), instructor);
                return 4; // id found and updated
            }
        }

        return 3; // id not found
    }





    public boolean deleteInstructor(String id) {
        for (Instructor ins : instructors) {
            if (ins.getId().equals(id)) {
                instructors.remove(instructors.indexOf(ins));
                return true; // Deleted
            }
        }
        return false; // ID not found
    }




    /// this method for check  if the instructor exist to sign him in certain course or not:
    public boolean isInstructorExists(String id) {
        for (Instructor ins : instructors) {
            if (ins.getId().equals(id))
                return true; // instructor exist
        }
        return false; // not exist
    }



    //extra end-point 1:
    public ArrayList<Instructor> getByStatus(String status) {
        ArrayList<Instructor> instructorList = new ArrayList<>();

        for (Instructor ins : instructors) {
            if(ins.getStatus().equalsIgnoreCase(status)){
                instructorList.add(ins);
            }
        }
        return instructorList;
    }



    // extra end-point 2:
    public ArrayList<Instructor> getByHireDate(LocalDate date) {
        ArrayList<Instructor> instructorList = new ArrayList<>();
        for (Instructor ins : instructors) {
            if (ins.getHireDate().isAfter(date)) {
                instructorList.add(ins);
            }
        }
        return instructorList;
    }


    // extra end-point 3:
    public ArrayList<Instructor> getByDepartment(String department) {
        ArrayList<Instructor> instructorList = new ArrayList<>();

        for (Instructor ins : instructors){
            if(ins.getDepartment().equalsIgnoreCase(department)){
                instructorList.add(ins);
            }
        }
        return instructorList;
    }


    // extra end-point 4:
    public ArrayList<Instructor> getByNamePartially(String letters) {
        ArrayList<Instructor> namesList = new ArrayList<>();
        int indexNums = letters.length();

        for (Instructor ins : instructors){
            if(ins.getFirstName().toLowerCase().contains(letters)){
                namesList.add(ins);
            }
        }
        return  namesList;
    }





}
