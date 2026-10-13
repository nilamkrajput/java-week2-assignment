package com.studentmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentManager;
import com.studentmanagement.util.FileHandler;

public class Main{
    private static final Scanner scanner = new Scanner(System.in);

    private static final StudentManager studentManager = new StudentManager();

    public static void main(String[] args){
        loadData();
        boolean option = true;
        while(option){
            showMenu();
            int choice = readInt("Enter your choice :");
            switch (choice){
                case 1 :
                    addStudent();
                    break;
                case 2 :
                    displayAll();
                    break;
                case 3 :
                    searchStudent();
                    break;
                case 4 :
                    updateStudent();
                    break;
                case 5 :
                    deleteStudent();
                    break;
                case 6 :
                    calculateAverage();
                    break;
                case 7:
                    saveData();
                    break;
                case 8 :
                    saveData();
                    System.out.println("Thank you for using the system");
                    option = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        }
        scanner.close();
    }
    private static void showMenu() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("       STUDENT MANAGEMENT SYSTEM");
        System.out.println("======================================");

        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Calculate Class Average");
        System.out.println("7. Save Data");
        System.out.println("8. Exit");

        System.out.println("======================================");
    }

    public static void addStudent(){
        System.out.println("-------- Add Student");
        int id;
        while (true){
            id = readInt("Enter Student ID : ");
            if(studentManager.findById(id) != null){
                System.out.println("ID already exists.");
            }
            if(id <= 0){
                System.out.println("Please enter valid ID.");
            }
            else{
                break;
            }
        }
        String name = readName();

        int age;
        while(true){
            age = readInt("Enter age : ");

            if (age >= 5 && age <= 100) {
                break;
            }
            System.out.println("Please enter valid age");
        }

        double grade;
        while(true){
            grade = readDouble("Enter grade : ");
            if(grade >= 0 && grade <= 100){
                break;
            }
            System.out.println("Grade must be between 0 and 100");
        }

        List<String> subjects = readSubjects();
        Student student = new Student(id, name, age,grade,subjects);
        if(studentManager.addStudent(student)){
            System.out.println("Student added successfully");
        }
        else{
            System.out.println("Failed to add student1");
        }
    }

    private static int  readInt(String message){
        while(true){
            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            }
            catch (NumberFormatException e){
                System.out.println("Please enter a valid number");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Enter proper number");
            }
        }
    }

    private static String readName(){
        return nonEmptyString("Enter Name : ");
    }
    private static String nonEmptyString(String message){
        while (true){
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if(!input.isEmpty()){
                return input;
            }
            System.out.println("Please enter value");
        }
    }
    private static List<String> readSubjects(){
        System.out.println("Enter subjects separated by comma :");
        String input = scanner.nextLine();
        return parseSubjects(input);
    }

    private static List<String> parseSubjects(String input){
        List<String> subjects = new ArrayList<>();
        String[] values = input.split(",");
        for(String value : values){
            String subject = value.trim();
            if(!subject.isEmpty()){
                subjects.add(subject);
            }
        }
        return subjects;
    }
    private static void displayAll(){
        List<Student> students = studentManager.getAllStudents();
        if(students.isEmpty()){
            System.out.println("No students available");
            return;
        }
        System.out.println();
        System.out.printf(
                "%-8s %-20s %-8s %-10s %-30s%n",
                "ID",
                "Name",
                "Age",
                "Grade",
                "Subjects"
        );
        System.out.println(
                "----------------------------------------------------------------------"
        );

        for(Student student : students){
            System.out.printf(
                    "%-8d %-20s %-8d %-10.2f %-30s%n",
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getGrade(),
                    String.join(", ", student.getSubjects())
            );
        }
    }
    private static void searchStudent(){

        System.out.println("\n--- Search Student ---");

        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        int choice = readInt("Choose Option : ");
        if(choice == 1){
            int id = readInt("Enter Student ID to search : ");
            Student student = studentManager.findById(id);
            if(student == null){
                System.out.println("Student not found");
            }
            else{
                displayStudent(student);
            }
        } else if (choice == 2) {
            String name = nonEmptyString("Enter Name to search : ");
            List<Student> students = studentManager.findByName(name);
        }
        else {
            System.out.println("Please select valid option");
        }
    }

    private static void updateStudent() {

        System.out.println("\n--- Update Student ---");

        int id = readInt("Enter student ID: ");

        Student student =
                studentManager.findById(id);

        if (student == null) {

            System.out.println("Student not found.");
            return;
        }

        System.out.println(
                "Press Enter to keep the existing value."
        );

        String name;

        System.out.print(
                "Name [" + student.getName() + "]: "
        );

        name = scanner.nextLine();

        Integer age = null;

        System.out.print(
                "Age [" + student.getAge() + "]: "
        );

        String ageInput = scanner.nextLine();

        if (!ageInput.trim().isEmpty()) {

            try {

                age = Integer.parseInt(ageInput);

                if (age < 5 || age > 100) {
                    System.out.println("Invalid age.");
                    return;
                }

            } catch (NumberFormatException e) {

                System.out.println("Invalid age.");
                return;
            }
        }

        Double grade = null;

        System.out.print(
                "Grade [" + student.getGrade() + "]: "
        );

        String gradeInput = scanner.nextLine();

        if (!gradeInput.trim().isEmpty()) {

            try {

                grade = Double.parseDouble(gradeInput);

                if (grade < 0 || grade > 100) {
                    System.out.println("Invalid grade.");
                    return;
                }

            } catch (NumberFormatException e) {

                System.out.println("Invalid grade.");
                return;
            }
        }

        System.out.println(
                "Current subjects: "
                        + String.join(", ", student.getSubjects())
        );

        System.out.print(
                "Enter new subjects separated by comma " +
                        "(Enter to keep existing): "
        );

        String subjectInput = scanner.nextLine();

        List<String> subjects = null;

        if (!subjectInput.trim().isEmpty()) {

            subjects = parseSubjects(subjectInput);
        }

        studentManager.updateStudent(
                id,
                name,
                age,
                grade,
                subjects
        );

        System.out.println("Student updated successfully.");
    }
    private static void deleteStudent(){
        // amazonq-ignore-next-line
        System.out.println("\n ------ Delete Student ------");
        int id = readInt("Enter student ID : ");

        Student student = studentManager.findById(id);
        if(student == null){
            System.out.println("Student not found");
            return;
        }
        System.out.println("Are you sure you want to delete? (Y/N) : ");
        String confirmation = scanner.nextLine();
        if(confirmation.equalsIgnoreCase("Y")){
            if(studentManager.deleteStudent(id)){
                System.out.println("Student deleted successfully.");
            }
            else{
                System.out.println("Can not deleted.");
            }
        }
    }

    private static void calculateAverage(){

        double average = studentManager.calculateAverage();

        System.out.printf(
                "Class Average Grade: %.2f%n",
                average
        );
    }

    private static void saveData(){
        FileHandler.saveStudents(studentManager.getAllStudents());
    }

    private static void loadData(){
        List<Student> students = FileHandler.loadStudents();
        for (Student s : students) {
            studentManager.addStudent(s);
        }
    }
    private static void displayStudent(Student student){

        System.out.println();
        System.out.printf(
                "%-8s %-20s %-8s %-10s %-30s%n",
                "ID",
                "Name",
                "Age",
                "Grade",
                "Subjects"
        );
        System.out.println(
                "----------------------------------------------------------------------"
        );


            System.out.printf(
                    "%-8d %-20s %-8d %-10.2f %-30s%n",
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getGrade(),
                    String.join(", ", student.getSubjects())
            );

    }
}