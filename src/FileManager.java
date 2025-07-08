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
            writer.write(student.toString());
            writer.newLine();

        }catch(IOException e){
            System.out.println("Error occured while saving the student" + e.getMessage());
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
                if(parts.length == 3){
                    String name = parts[0];
                    int roll = Integer.parseInt(parts[1]);
                    int age = Integer.parseInt(parts[2]);
                    list.add(new Student(name, roll, age));
                }
            }

            
        } catch(IOException e){
            System.out.println("Error occured while loading the students: " + e.getMessage());
        }

        return list;
    }
}
