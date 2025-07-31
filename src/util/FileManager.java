package util;
import java.util.*;

import student.Department;
import student.Grade;
import student.Student;

import java.io.*;



public class FileManager {
    private static final String FILE_NAME = "src/data/students.txt";
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
                System.out.println(s);
                writer.write(formatStudent(s));
                writer.newLine();
            }
        }catch(IOException e){
            System.out.println("Error occurred while overwriting the file: " + e.getMessage());
        }
    }
    // will use it later
    public static void appendCredential(String username, String password, String role, int studentRoll) {
    try (FileWriter fw = new FileWriter("src/data/credentials.txt", true);
         BufferedWriter bw = new BufferedWriter(fw);
         PrintWriter out = new PrintWriter(bw)) {

        out.println(username + "," + password + "," + role + "," + studentRoll);
    } catch (IOException e) {
        System.out.println("❌ Error writing to credentials file: " + e.getMessage());
    }
}

    public static void removeCredentialByRoll(int rollno){
        File  inputFile = new File("src/data/credentials.txt");
        File tempfile = new File("src/data/credentials_temp.txt");

        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(tempfile))) {

            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length >= 4 && !parts[3].equals(rollno)){
                    writer.println(line);
                }
            }
        } catch(IOException e){
            System.out.println("Error occurred while removing credential: " + e.getMessage());
            return;
        }
        if(!inputFile.delete() || !tempfile.renameTo(inputFile)){
            System.out.println("Error occurred while renaming the temporary file.");
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
                if(parts.length >= 4){
                    String name = parts[0];
                    int roll = Integer.parseInt(parts[1]);
                  
                    Department department = Department.valueOf(parts[2]);
                   
                    Map<String, Grade> subjects = new HashMap<>();

                    if(!parts[3].isEmpty()){
                        String[] subPairs = parts[3].split("\\|");
                        for(String pair: subPairs){
                            String[] subGrade = pair.split(":");
                            if(subGrade.length == 2){
                                String subject = subGrade[0];
                                Grade grade = Grade.valueOf(subGrade[1]);
                                subjects.put(subject, grade);
                            }
                        }
                    }
                    Student s = new Student(name, roll, department, subjects);
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
        // sb.append(student.getAge()).append(",");
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
