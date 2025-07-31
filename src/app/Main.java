package app;

import util.FileManager;
import java.util.ArrayList;
import java.util.Scanner;

import auth.AuthManager;
import models.Admin;
import models.StudentUser;
import models.User;
import services.AdminService;
import services.StudentService;
import student.Student;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = FileManager.loadStudents();
        
     System.out.print("Enter username: ");
     String username = sc.nextLine().trim();

     System.out.print("Enter password: ");     
     String password = sc.nextLine().trim();
        User user = AuthManager.login(username, password, students);

        if (user instanceof Admin) {
            AdminService adminService = new AdminService(sc, students);

            while (true) {
                System.out.println("\n--- Admin Dashboard ---");
                System.out.println("1. Add Student");
                System.out.println("2. Delete Student");
                System.out.println("3. View All Students");
                System.out.println("4. Search Student by Roll");
                System.out.println("5. Sort Students by Name");
                System.out.println("6. Update Student");
                System.out.println("7. Reset Student Password");
                System.out.println("8. Logout");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> adminService.addStudent();
                    case 2 -> adminService.deleteStudent();
                    case 3 -> adminService.viewAllStudents();
                    case 4 -> {
                        System.out.print("Enter roll number to search: ");
                        int roll = sc.nextInt();
                        adminService.searchStudentByRoll(roll);
                    }
                    case 5 -> adminService.sortStudentsByName();
                    case 6 -> adminService.updateStudent();
                    case 7 -> adminService.resetStudentPassword();
                    case 8 -> {
                        System.out.println("Logged out.");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }

        } else if (user instanceof StudentUser) {
            StudentUser studentUser = (StudentUser) user;

            while (true) {
                System.out.println("\n--- Student Dashboard ---");
                System.out.println("1. View My Details");
                System.out.println("2. Logout");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> {
                        Student student = studentUser.getStudent();
                        if (student != null) {
                            StudentService studentService = new StudentService(sc, student);
                            studentService.viewProfile(); 
                        } else {
                            System.out.println("Student details not found.");
                        }
                    }
                    case 2 -> {
                        System.out.println("Logged out.");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Unrecognized user role.");
        }
    }
}
