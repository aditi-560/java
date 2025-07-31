package models;
// links to a student

import student.Student;

public class StudentUser extends User {
    private Student student;  // linked student object

    public StudentUser(String username, String password, Student student){
        super(username, password);
        this.student = student;
    }

    @Override

    public String getRole(){
        return "student";
    }
           public Student getStudent() {
        return student;
    }
    public void setStudent(Student student){
         this.student = student;
    }
    
}
