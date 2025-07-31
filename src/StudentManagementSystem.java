// no use of this file this for referance and implementation created by me


import java.util.*;

import student.Department;
import student.Grade;
import student.Student;
import util.FileManager;


public class StudentManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> studentsList = FileManager.loadStudents();
        int choice;

        do{
            System.out.println("1 Add Student");
            System.out.println("2 Display Students");
            System.out.println("3 Search Student");
            System.out.println("4 Delete the student");
            System.out.println("5. Top performing students");
            System.out.println("6. Sort Students");
            System.out.println("7. Exit the system");
            System.out.println("Enter you choice");

            choice = sc.nextInt();

            switch(choice) {
                case 1:
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

            // System.out.println("Enter city: ");
            // String city = sc.nextLine();
            // System.out.println("Em=nter State : ");;
            // String state = sc.nextLine();
            // System.out.println("Enter pin code: "); 
            // String pin = sc.nextLine();

            // Student.Address address = new Student.Address(city, state, pin);



                Student s = new Student(name, roll, dept, subjectGrades);
                studentsList.add(s);
                FileManager.saveStudent(s);
                System.out.println("Student added successfully!");
                break;

                case 2:
                if(studentsList.isEmpty()){
                    System.out.println("No students availabe");
                }else{
                    for(Student student: studentsList){
                        student.display();
                    }
                }
                break;


                case 3:
                System.out.println("Enter roll number to search:");
                int searchRoll = sc.nextInt();
                boolean found = false;
                for(Student student: studentsList){
                    if(student.getRollNo() == searchRoll){
                        found =  true;
                        student.display();
                        break;
                    }


                }
                if(!found){
                    System.out.println("Student with roll number " + searchRoll + " not found.");
                }
                break;

                case 4:
                System.out.println("Enter roll number to delete:");
                int delroll = sc.nextInt();
                boolean is = false;
                
                Iterator<Student> iterator = studentsList.iterator();
                while(iterator.hasNext()){
                    Student student  = iterator.next();
                    
                        if(student.getRollNo() == delroll){
                            iterator.remove();
                            FileManager.overwriteFile(studentsList);
                            System.out.println("Student with roll number " + delroll + " deleted successfully.");
                            is = true;
                            break;
                        
                    }
                }
                if(!is){
                    System.out.println("Student with roll number " + delroll + " not found.");
                }
                break;

                
                case 5:
                System.out.println("How many top peforming students do you want to display>");;
                int topN = sc.nextInt();
                
                studentsList.stream()
                .sorted((s1, s2) -> Double.compare(s2.calculateGPA(), s1.calculateGPA()))
                .limit(topN)
                .forEach(Student::display);
                break;

                case 6:
                if(studentsList.isEmpty()){
                    System.out.println("No student is available in the array to sort");
                    break;
                }

                System.out.println("\nChoose sorting criteria: ");
                System.out.println("1. Name");
                System.out.println("2. Age");
                System.out.println("3. Roll Number");
                System.out.println("4. Department");
                System.out.println("Enter your choice: ");
                int sortChoice = sc.nextInt();

                Comparator<Student> comparator = null;

                switch(sortChoice){

                    case 1:
                    comparator= Comparator.comparing(Student::getName);
                    break;

                    case 2:
                    comparator = Comparator.comparingInt(Student::getAge);
                    break;

                    case 3:
                    comparator = Comparator.comparingInt(Student::getRollNo);
                    break;

                    case 4:
                    comparator = Comparator.comparing(Student::getDepartment);
                    break;

                    default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
                }

                if(comparator != null){
                    System.out.println("\nSorted Students: ");
                    studentsList.stream()
                    .sorted(comparator)
                    .forEach(Student::display);
                }
                break;
                
                case 7:
                System.out.println("Exiting the system. Goodbye!");
                break;
                default:
                System.out.println("Invalid number entered");


            }


        } while(choice!=7);
        sc.close();

    }
}
