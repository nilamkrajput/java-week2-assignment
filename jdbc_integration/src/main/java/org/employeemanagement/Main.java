package org.employeemanagement;



import org.employeemanagement.dao.EmployeeDao;
import org.employeemanagement.model.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EmployeeDao employeeDao =
                new EmployeeDao();


        // ==========================================
        // 1. CREATE TABLE
        // ==========================================

        System.out.println(
                "\n========== CREATE TABLE =========="
        );

        employeeDao.createTable();


        // ==========================================
        // 2. INSERT EMPLOYEE
        // ==========================================

        System.out.println(
                "\n========== INSERT =========="
        );

        Employee employee1 =
                new Employee(
                        "Rahul Sharma",
                        "rahul@mail.com",
                        "IT",
                        "Backend Developer",
                        new BigDecimal("65000.00"),
                        "9876543210",
                        LocalDate.of(2025, 6, 10)
                );

        employeeDao.insertRecord(employee1);


        Employee employee2 =
                new Employee(
                        "Priya Patel",
                        "priya@mail.com",
                        "HR",
                        "HR Manager",
                        new BigDecimal("75000.00"),
                        "9876543211",
                        LocalDate.of(2024, 8, 15)
                );

        employeeDao.insertRecord(employee2);


        Employee employee3 =
                new Employee(
                        "Amit Shah",
                        "amit@mail.com",
                        "Finance",
                        "Accountant",
                        new BigDecimal("55000.00"),
                        "9876543212",
                        LocalDate.of(2023, 3, 20)
                );

        employeeDao.insertRecord(employee3);


        // ==========================================
        // 3. SELECT
        // ==========================================

        System.out.println(
                "\n========== SELECT =========="
        );

        displayEmployees(
                employeeDao.getAllRecords()
        );


        // ==========================================
        // 4. UPDATE
        // ==========================================

        System.out.println(
                "\n========== UPDATE =========="
        );

        Employee updatedEmployee =
                new Employee(
                        1,
                        "Rahul Sharma",
                        "rahul.sharma@mail.com",
                        "IT",
                        "Senior Backend Developer",
                        new BigDecimal("85000.00"),
                        "9876543210",
                        LocalDate.of(2025, 6, 10)
                );

        employeeDao.updateRecord(
                updatedEmployee
        );


        // ==========================================
        // SELECT AFTER UPDATE
        // ==========================================

        System.out.println(
                "\n========== AFTER UPDATE =========="
        );

        displayEmployees(
                employeeDao.getAllRecords()
        );


        // ==========================================
        // 5. DELETE
        // ==========================================

        System.out.println(
                "\n========== DELETE =========="
        );

        employeeDao.deleteRecord(3);


        // ==========================================
        // SELECT AFTER DELETE
        // ==========================================

        System.out.println(
                "\n========== AFTER DELETE =========="
        );

        displayEmployees(
                employeeDao.getAllRecords()
        );
    }


    // ==========================================
    // DISPLAY EMPLOYEES
    // ==========================================

    private static void displayEmployees(
            List<Employee> employees
    ) {

        if (employees.isEmpty()) {

            System.out.println(
                    "No employees found."
            );

            return;
        }

        for (Employee employee : employees) {

            System.out.println(employee);
        }
    }
}
