import java.util.*;



public class Student {
    private String name;
    private int age;
    private int rollno;
    private Department department;
    private Map<String, Grade> subjects = new HashMap<>();


    public void addSubject(String subject, Grade grade){
        subjects.put(subject, grade);
    }
    public Student(String name, int rollno, int age, Department department, Map<String, Grade> subjects){
        this.name = name;
        this.age = age;
        this.rollno = rollno;
        this.department = department;
        this.subjects = subjects;

    }

    public double calculateGPA() {
    if (subjects.isEmpty()) return 0.0;

    return subjects.values()
                        .stream()
                        .mapToDouble(Grade::getPoints)
                        .average()
                        .orElse(0.0);
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
    public Map<String, Grade> getSubjectGrades(){
        return subjects;
    }
    public void display(){
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Roll No: " + rollno);
        System.out.println("Department: " + department.getFullName());
        System.out.println("Subjects & Grades: ");
        subjects.forEach((sub, grade) -> 
        System.out.println(" - " + sub + ": " + grade + " (" + grade.getPoints() + ") "));
        System.out.printf("GPA : %.2f\n" , calculateGPA());
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
    @Override
    public String toString(){
        return name + "," + rollno + "," + age +  "," + department.name();
    }
}
