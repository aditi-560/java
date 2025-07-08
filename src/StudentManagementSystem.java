import java.util.*;


public class StudentManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> studentsList = new ArrayList<>();
        int choice;

        do{
            System.out.println("1 Add Student");
            System.out.println("2 Display Students");
            System.out.println("3 Search Student");
            System.out.println("4 Exist the system");
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

                Student s = new Student(name, roll, age);
                studentsList.add(s);
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
                System.out.println("Exiting the system. Goodbye!");
                break;

                default:
                System.out.println("Invalid number entered");
                


            }


        } while(choice!=4);
        sc.close();

    }
}
