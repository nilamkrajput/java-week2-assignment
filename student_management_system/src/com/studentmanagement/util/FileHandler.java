package com.studentmanagement.util;

import com.studentmanagement.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_PATH = "data/students.dat";

    public static void saveStudents(List<Student> students){
        File file = new File(FILE_PATH);
        File parent = file.getParentFile();
        if(parent != null && !parent.exists()){
            parent.mkdirs();
        }
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(students);
            System.out.println("Data saved successfully");

        } catch (IOException e) {
            System.out.println(
                    "Error while saving data: " + e.getMessage());
        }

    }
    @SuppressWarnings("unchecked")
    public static List<Student> loadStudents(){
        File file = new File(FILE_PATH);
        if(!file.exists()){
            return new ArrayList<>();
        }
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Student>) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e){
            System.out.println("Error while loading data: "+e.getMessage());
            return new ArrayList<>();
        }
    }
}
