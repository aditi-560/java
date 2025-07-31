

package auth;
import java.util.*;
import java.io.*;


import models.StudentUser;
import models.User;
import models.Admin;
import student.Student;


public class AuthManager {
    private static final String CREDENTIALS_FILE = "src/data/credentials.txt";

    public static User login(String username, String password, List<Student> students){
        try(BufferedReader reader  = new BufferedReader(new FileReader(CREDENTIALS_FILE))){
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length != 4) continue; // Invalid format}
                String fileUsername = parts[0].trim();
                String filePassword = parts[1].trim();
                String role = parts[2].trim();
                String rollStr = parts[3].trim();

                if(fileUsername.equals(username) && filePassword.equals(password)){
                    if(role.equalsIgnoreCase("admin")){
                        return new Admin(username, password);
                    } else if(role.equalsIgnoreCase("student")){
                        int rollNumber = Integer.parseInt(rollStr);
                        for(Student s: students){
                            if(s.getRollNo() == rollNumber){
                                return new StudentUser(username, password, s);
                            }
                        }
                    }
                }

            }
        }
        catch (IOException e){
            System.out.println("Error reading credentials : "+ e.getMessage());
        }
        return null;
    }

    public static void updatePassword(int rollNumber, String newPassword) {
    File inputFile = new File("src/data/credentials.txt");
    File tempFile = new File("src/data/temp_credentials.txt");

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

        String line;
        boolean updated = false;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length != 4) continue;

            String fileUsername = parts[0].trim();
            String role = parts[2].trim();
            String fileRollNumber = parts[3].trim();

            if (fileRollNumber.equals(String.valueOf(rollNumber))) {
                writer.write(fileUsername + "," + newPassword + "," + role + "," + fileRollNumber);
                updated = true;
            } else {
                writer.write(line);
            }
            writer.newLine();
        }

    } catch (IOException e) {
        System.out.println("Error updating credentials file: " + e.getMessage());
        return;
    }

    // ✅ After try-with-resources ends, move/replace files
    File original = new File("src/data/credentials.txt");
    File temp = new File("src/data/temp_credentials.txt");

    if (original.delete()) {
        if (temp.renameTo(original)) {
            System.out.println("Password updated and file replaced successfully.");
        } else {
            System.out.println("❌ Failed to rename temp file to original.");
        }
    } else {
        System.out.println("❌ Failed to delete original file.");
    }
}


}
