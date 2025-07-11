import java.util.*;


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
            System.out.println("5.exit the system");
            System.out.println("Enter you choice");

            choice = sc.nextInt();

            switch(choice) {
                case 1:
                System.out.println("enter name");
                sc.nextLine();
                String name = sc.nextLine();
                System.out.println("enter roll no");
                int roll = sc.nextInt();
                System.out.println("enter age");
                int age = sc.nextInt();
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


                Student s = new Student(name, roll, age, dept);
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
                System.out.println("Exiting the system. Goodbye!");
                break;

                default:
                System.out.println("Invalid number entered");


            }


        } while(choice!=4);
        sc.close();

    }
}
