package services;
import student.Grade;
import student.Student;
import java.util.*;

public class StudentService {
    private Scanner sc;
    private Student loggedInStudent;

    public StudentService(Scanner sc, Student loggedInStudent) {
        this.sc = sc;
        this.loggedInStudent = loggedInStudent;



    }

    public void viewProfile(){
        System.out.println("Your profile: ");
        System.out.println("Name: " + loggedInStudent.getName());
        System.out.println("Roll No: " + loggedInStudent.getRollNo());
        System.out.println("Department: " + loggedInStudent.getDepartment());
        System.out.println("Subjects and Grades: ");
        for (Map.Entry<String, Grade> entry : loggedInStudent.getSubjectGrades().entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }


}
