import java.util.*;



public class Student {
    private String name;
    private int age;
    private int rollno;

    public Student(String name, int rollno, int age){
        this.name = name;
        this.age = age;
        this.rollno = rollno;
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
    public void display(){
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Roll No: " + rollno);
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
    @Override
    public String toString(){
        return name + "," + rollno + "," + age;
    }
}
