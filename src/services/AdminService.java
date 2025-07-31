package services;
import java.util.*;

import student.Department;
import student.Grade;
import student.Student;
import util.FileManager;
import auth.AuthManager;

public class AdminService {
    private Scanner sc;
    private ArrayList<Student> students;

    public AdminService(Scanner sc, ArrayList<Student> student){
        this.sc = sc;
        this.students = student;
    }

    public void addStudent(){
        System.out.println("enter name");
                sc.nextLine();
                String name = sc.nextLine();
                System.out.println("enter roll no");
                int roll = sc.nextInt();
                
                boolean valid  = false;
                Department dept = null;
                System.out.println("The departments available here are : ");
                
                while(!valid){
                for(Department d : Department.values()){
                    System.out.println("- " + d.name());;
                }

                System.out.print("Enter Department: " );
                sc.nextLine();
                String deptInput = sc.nextLine().trim().toUpperCase();
                
                try{
                    dept = Department.valueOf(deptInput);
                    valid = true;
                } catch(IllegalArgumentException e){
                    System.out.println("Invalid department. Please enter a valid department.");
                }
            }

            System.out.println("Enter number of subjects: ");
            int subjectCount= sc.nextInt();
            sc.nextLine();

            Map<String, Grade> subjectGrades = new HashMap<>();

            for(int i = 0;i<subjectCount;i++){
                System.out.println("Enter subject " + (i+1) + ":");
                String subject = sc.nextLine();
                System.out.println("Enter grade for " + subject + "(A, B, C, D, E, F)");
                String gradeInput = sc.nextLine().trim().toUpperCase();

                try{
                    Grade grade = Grade.valueOf(gradeInput);
                    subjectGrades.put(subject, grade);
                }catch(IllegalArgumentException e){
                    System.out.println("Invalid grade entered. Please enter a valid grade (A, B, C, D, F).");
                    i--; // to repeat the input for the same subject
                }
            }





        System.out.println("Create username for student: ");;
        String username= sc.nextLine();

        System.out.println("Create password for student: ");
        String password = sc.nextLine();

        Student s = new Student(name, roll, dept, subjectGrades);


        students.add(s);

        FileManager.saveStudent(s);

        FileManager.appendCredential(username, password, "student" ,roll);

        
    }

    public void deleteStudent(){
        System.out.println("Enter roll number to delete:");
                int delroll = sc.nextInt();
                boolean is = false;
                
                Iterator<Student> iterator = students.iterator();
                while(iterator.hasNext()){
                    Student student  = iterator.next();
                    
                        if(student.getRollNo() == delroll){
                            iterator.remove();
                            FileManager.overwriteFile(students);
                            System.out.println("Student with roll number " + delroll + " deleted successfully.");
                            is = true;
                            break;
                        
                    }
                }
                if(!is){
                    System.out.println("Student with roll number " + delroll + " not found.");
                }
    }

    public void viewAllStudents(){
       if(students.isEmpty()){
                    System.out.println("No students availabe");
                }else{
                    for(Student student: students){
                        student.display();
                    }
                }
        }
    

    public void searchStudentByRoll(int roll) {
        // boolean found = false;
    for (Student s : students) {
        if (s.getRollNo() == roll) {
            System.out.println("Student Found:");
            System.out.println(s);
            return;
        }
    }
    System.out.println("Student with the given roll number not found.");
}

    public void sortStudentsByName(){
        Collections.sort(students, Comparator.comparing(Student::getName));
        System.out.println("Students sorted by name:");
        for(Student s: students){
            System.out.println(s);
        }

    }

public void updateStudent() {
    System.out.println("Enter Roll Number of student to update: ");
    int roll = sc.nextInt();
    sc.nextLine(); // consume newline

    Student target = null;
    for (Student s : students) {
        if (s.getRollNo() == roll) {
            target = s;
            break;
        }
    }

    if (target == null) {
        System.out.println("Student with roll number " + roll + " not found.");
        return;
    }

    boolean updating = true;

    while (updating) {
        System.out.println("\nWhat would you like to update?");
        System.out.println("1. Name");
        System.out.println("2. Department");
        System.out.println("3. Exit");

        System.out.print("Enter choice: ");
        String input = sc.nextLine();

        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            continue;
        }

        switch (choice) {
            case 1:
                System.out.print("Enter new name: ");
                String newName = sc.nextLine();
                System.out.println("Before: " + target.getName());
                target.setName(newName);
                System.out.println("After: " + target.getName());

                System.out.println("Name updated successfully.");
                break;

            case 2:
                boolean validDept = false;
                Department newDept = null;
                while (!validDept) {
                    System.out.println("Available Departments:");
                    for (Department d : Department.values()) {
                        System.out.println("- " + d.name());
                    }
                    System.out.print("Enter new department: ");
                    String deptInput = sc.nextLine().trim().toUpperCase();
                    try {
                        newDept = Department.valueOf(deptInput);
                        validDept = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid department. Try again.");
                    }
                }
                target.setDepartment(newDept);
                System.out.println("Department updated successfully.");
                break;

            case 3:
                updating = false;
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    FileManager.overwriteFile(students);
    System.out.println("Student information updated successfully.");
}


    public void resetStudentPassword(){
        System.out.println("Enter Roll Number of Student to reset password");;
        int  roll = sc.nextInt();
        sc.nextLine();

        boolean studentExists = false;
        for(Student s: students){
            if(s.getRollNo() == roll){
                studentExists = true;
                break;
            }
        }
        if(!studentExists){
            System.out.println("Student with roll number " + roll + " not found.");
            return;
        }

        System.out.println("Enter new password for student with roll number " + roll + ": ");
        String newPassword = sc.nextLine();

        AuthManager.updatePassword(roll, newPassword);
        System.out.println("Password for student with roll number " + roll + " has been reset successfully.");

    }




}
