package com.studentmanagement.service;

import com.studentmanagement.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private final List<Student> students;

    public StudentManager(){
        students = new ArrayList<>();
    }

    
    public Student findById(int id){
        for(Student student : students){
            if(student.getId() == id){
                return student;
            }
        }
        return null;
    }

    public List<Student> findByName(String name){
        List<Student> result = new ArrayList<>();
        for(Student student : students){
            if(student.getName().toLowerCase().contains(name.toLowerCase())){
                result.add(student);
            }
        }
        return result;
    }

    public boolean deleteStudent(int id){
        Student student = findById(id);
        if(student == null){
            return false;
        }
        students.remove(student);
        return true;
    }

    public double calculateAverage(){
        if(students.isEmpty()){
            return 0;
        }
        double total = 0;
        for(Student student : students){
           total += student.getGrade();
        }
        return total / students.size();
    }

    public boolean addStudent(Student student){
        if(findById(student.getId()) != null){
            return false;
        }
        students.add(student);
        return true;
    }
    public List<Student> getAllStudents(){
        return new ArrayList<>(students);
    }
    public boolean updateStudent(
            int id,
            String name,
            Integer age,
            Double grade,
            List<String> subjects) {

        Student student = findById(id);

        if (student == null) {
            return false;
        }

        if (name != null && !name.trim().isEmpty()) {
            student.setName(name);
        }

        if (age != null) {
            student.setAge(age);
        }

        if (grade != null) {
            student.setGrade(grade);
        }

        if (subjects != null) {
            student.setSubjects(subjects);
        }

        return true;
    }
}
