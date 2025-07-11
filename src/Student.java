import java.util.*;



public class Student {
    private String name;
    private int age;
    private int rollno;
    private Department department;

    public Student(String name, int rollno, int age, Department department){
        this.name = name;
        this.age = age;
        this.rollno = rollno;
        this.department = department;

    }

    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public int getRollNo(){
        return rollno;
    }
    public Department getDepartment(){
        return department;
    }
    public void display(){
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Roll No: " + rollno);
        System.out.println("Department: " + department.getFullName());
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
    @Override
    public String toString(){
        return name + "," + rollno + "," + age +  "," + department.name();
    }
}
