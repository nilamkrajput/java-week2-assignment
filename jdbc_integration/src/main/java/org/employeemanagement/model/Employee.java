package org.employeemanagement.model;


import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {

    private int id;
    private String name;
    private String email;
    private String department;
    private String designation;
    private BigDecimal salary;
    private String phone;
    private LocalDate joiningDate;

    // Default constructor
    public Employee() {
    }

    // Constructor without ID
    public Employee(
            String name,
            String email,
            String department,
            String designation,
            BigDecimal salary,
            String phone,
            LocalDate joiningDate
    ) {

        this.name = name;
        this.email = email;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
        this.phone = phone;
        this.joiningDate = joiningDate;
    }

    // Constructor with ID
    public Employee(
            int id,
            String name,
            String email,
            String department,
            String designation,
            BigDecimal salary,
            String phone,
            LocalDate joiningDate
    ) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
        this.phone = phone;
        this.joiningDate = joiningDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    @Override
    public String toString() {

        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", designation='" + designation + '\'' +
                ", salary=" + salary +
                ", phone='" + phone + '\'' +
                ", joiningDate=" + joiningDate +
                '}';
    }
}
