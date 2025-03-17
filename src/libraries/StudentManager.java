package libraries;

import model.Student;
import alumnosjswing.StudentRegistry;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
    private static File file;
    private BufferedReader br;
    public static ArrayList<Student> students;
    private Scanner sc;

    public StudentManager(String fileName) throws IOException {
        file = new File(System.getProperty("user.dir") + File.separator + fileName);
        students = new ArrayList<>();


        if (!file.exists()) {
            file.createNewFile();
        }

        br = new BufferedReader(new FileReader(file));
        loadStudents();
    }

    private void loadStudents() throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(";");
            students.add(new Student(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4]));
        }
    }


    public static int addStudent(String firstname, String lastname, int age, String course, String dni) throws IOException {
        
        
        
        
         /*Verify all fields are filled*/
        
        if ( firstname == null || firstname.isEmpty() || lastname == null || lastname.isEmpty() ||  course == null || course.isEmpty() || dni == null || dni.isEmpty()) {
            
            return 3;
            
        }
        
        
        /*Verify DNI*/
        if (!(dni.matches("\\d{8}[A-HJ-NP-TV-Z]"))) {
            return 2;
        }
        
       /*VERIFY age*/
      
        if (!(age >= 0 && age <= 120)) {
            return 1;
        }
        
        
        
        
        students.add(new Student(dni, firstname, lastname, age, course));
        System.out.println("Student added-");
        System.out.println("DNI: " +dni);
        System.out.println("First Name: " + firstname);
        System.out.println("Last name: " +lastname);
        System.out.println("Age: "+age);
        System.out.println("Course: "+course);
        System.out.println("-");

        return 0;
        
    }
    
    public static ArrayList<Student> getStudentArrayList(){
        return students;
    }



    public static void removeStudent(String name) {
        
        boolean deleted = true;
        
        
       
            students.removeIf(n -> (n.getFirstName()+" "+n.getLastName()).equals(name));         

            System.out.println(name + " deleted.");
        
        
        
    }

    public void searchStudent() {
        sc.nextLine();
        System.out.println("ID OF THE STUDENT TO SEARCH:");
        String id = sc.nextLine();

        boolean found = false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                System.out.println("ID: " + id);
                System.out.println("First Name: " + students.get(i).getFirstName());
                System.out.println("Last Name: " + students.get(i).getLastName());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found");
        }
    }

    public static void updateFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < students.size(); i++) {
            try {
                bw.write(students.get(i).getId() + ";"
                + students.get(i).getFirstName() + ";"
                + students.get(i).getLastName() + ";"
                + students.get(i).getAge() + ";"
                + students.get(i).getCourse()
                + System.getProperty("line.separator")
                );
                bw.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
