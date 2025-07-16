import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.FileReader;
import java.io.*;



public class FileManager {
    private static final String FILE_NAME = "students.txt";
    public static void saveStudent(Student student){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(formatStudent(student));
            writer.newLine();

        }catch(IOException e){
            System.out.println("Error occured while saving the student" + e.getMessage());
        }
    }

    public static void overwriteFile(List<Student> students){
        try(BufferedWriter writer  = new BufferedWriter(new FileWriter(FILE_NAME))){
            for(Student s : students){
                writer.write(formatStudent(s));
                writer.newLine();
            }
        }catch(IOException e){
            System.out.println("Error occurred while overwriting the file: " + e.getMessage());
        }
    }

    // to load the student
    public static ArrayList<Student> loadStudents(){
        ArrayList<Student> list = new ArrayList<>();
        File file  = new File(FILE_NAME);
        if(!file.exists()) return list;

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while((line = reader.readLine())!=null) {
                String[] parts = line.split(",");
                if(parts.length >= 7){
                    String name = parts[0];
                    int roll = Integer.parseInt(parts[1]);
                    int age = Integer.parseInt(parts[2]);
                    Department department = Department.valueOf(parts[3]);
                    String city = parts[4];
                    String state = parts[5];
                    String pin = parts[6];

                    Student.Address address = new Student.Address(city, state, pin);
                    Map<String, Grade> subjects = new HashMap<>();

                    if(parts.length == 8 && !parts[7].isEmpty()){
                        String[] subPairs = parts[7].split("\\|");
                        for(String pair: subPairs){
                            String[] subGrade = pair.split(":");
                            if(subGrade.length == 2){
                                String subject = subGrade[0];
                                Grade grade = Grade.valueOf(subGrade[1]);
                                subjects.put(subject, grade);
                            }
                        }
                    }
                    Student s = new Student(name, roll, age, department, subjects, address);
                    list.add(s);
                }
            }

            
        } catch(IOException e){
            System.out.println("Error occured while loading the students: " + e.getMessage());
        }

        return list;
    }

    private static String formatStudent(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(student.getName()).append(",");
        sb.append(student.getRollNo()).append(",");
        sb.append(student.getAge()).append(",");
        sb.append(student.getDepartment()).append(",");

        Map<String, Grade> grades = student.getSubjectGrades();
        for (Map.Entry<String, Grade> entry : grades.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append("|");
        }

        if (!grades.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1); // remove last "|"
        }

        return sb.toString();
    }
}
