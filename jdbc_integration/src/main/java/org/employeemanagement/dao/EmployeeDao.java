package org.employeemanagement.dao;


import org.employeemanagement.config.DatabaseConnection;
import org.employeemanagement.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {


    // ==========================================
    // CREATE TABLE
    // ==========================================

    public void createTable() {

        String sql =
                "CREATE TABLE IF NOT EXISTS employees (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "name VARCHAR(100) NOT NULL, " +
                        "email VARCHAR(150) NOT NULL UNIQUE, " +
                        "department VARCHAR(100) NOT NULL, " +
                        "designation VARCHAR(100), " +
                        "salary DECIMAL(10,2), " +
                        "phone VARCHAR(20), " +
                        "joining_date DATE" +
                        ")";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                Statement statement =
                        connection.createStatement()
        ) {

            statement.executeUpdate(sql);

            System.out.println(
                    "Employees table created successfully."
            );

        } catch (SQLException e) {

            System.err.println(
                    "Error creating employees table: "
                            + e.getMessage()
            );
        }
    }


    // ==========================================
    // INSERT EMPLOYEE
    // ==========================================

    public void insertRecord(Employee employee) {



        String sql =
                "INSERT INTO employees " +
                        "(name, email, department, designation, salary, phone, joining_date) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    employee.getName()
            );

            statement.setString(
                    2,
                    employee.getEmail()
            );

            statement.setString(
                    3,
                    employee.getDepartment()
            );

            statement.setString(
                    4,
                    employee.getDesignation()
            );

            statement.setBigDecimal(
                    5,
                    employee.getSalary()
            );

            statement.setString(
                    6,
                    employee.getPhone()
            );

            statement.setDate(
                    7,
                    Date.valueOf(employee.getJoiningDate())
            );

            int rows = statement.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Employee inserted successfully."
                );
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error inserting employee: "
                            + e.getMessage()
            );
        }
    }


    // ==========================================
    // SELECT ALL EMPLOYEES
    // ==========================================

    public List<Employee> getAllRecords() {

        List<Employee> employees =
                new ArrayList<>();

        String sql = "SELECT * FROM employees";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Employee employee =
                        new Employee();

                employee.setId(
                        resultSet.getInt("id")
                );

                employee.setName(
                        resultSet.getString("name")
                );

                employee.setEmail(
                        resultSet.getString("email")
                );

                employee.setDepartment(
                        resultSet.getString("department")
                );

                employee.setDesignation(
                        resultSet.getString("designation")
                );

                employee.setSalary(
                        resultSet.getBigDecimal("salary")
                );

                employee.setPhone(
                        resultSet.getString("phone")
                );

                Date joiningDate =
                        resultSet.getDate("joining_date");

                if (joiningDate != null) {

                    employee.setJoiningDate(
                            joiningDate.toLocalDate()
                    );
                }

                employees.add(employee);
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error retrieving employees: "
                            + e.getMessage()
            );
        }

        return employees;
    }


    // ==========================================
    // UPDATE EMPLOYEE
    // ==========================================

    public void updateRecord(Employee employee) {

        String sql =
                "UPDATE employees SET " +
                        "name = ?, " +
                        "email = ?, " +
                        "department = ?, " +
                        "designation = ?, " +
                        "salary = ?, " +
                        "phone = ?, " +
                        "joining_date = ? " +
                        "WHERE id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    employee.getName()
            );

            statement.setString(
                    2,
                    employee.getEmail()
            );

            statement.setString(
                    3,
                    employee.getDepartment()
            );

            statement.setString(
                    4,
                    employee.getDesignation()
            );

            statement.setBigDecimal(
                    5,
                    employee.getSalary()
            );

            statement.setString(
                    6,
                    employee.getPhone()
            );

            statement.setDate(
                    7,
                    Date.valueOf(employee.getJoiningDate())
            );

            statement.setInt(
                    8,
                    employee.getId()
            );

            int rows =
                    statement.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Employee updated successfully."
                );

            } else {

                System.out.println(
                        "Employee not found."
                );
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error updating employee: "
                            + e.getMessage()
            );
        }
    }


    // ==========================================
    // DELETE EMPLOYEE
    // ==========================================

    public void deleteRecord(int id) {

        String sql =
                "DELETE FROM employees WHERE id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            int rows =
                    statement.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Employee deleted successfully."
                );

            } else {

                System.out.println(
                        "Employee not found."
                );
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error deleting employee: "
                            + e.getMessage()
            );
        }
    }
}
