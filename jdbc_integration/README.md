# JDBC Integration — Employee Management System

A Java Maven project demonstrating JDBC connectivity with MySQL using the **DAO (Data Access Object)** pattern to perform full CRUD operations on an `employees` table.

---

## Project Structure

```
jdbc_integration/
├── database/
│   └── schema.sql                  # SQL schema (CREATE TABLE)
├── src/main/java/org/employeemanagement/
│   ├── config/
│   │   └── DatabaseConnection.java # JDBC connection utility
│   ├── dao/
│   │   └── EmployeeDao.java        # DAO — CRUD operations
│   ├── model/
│   │   └── Employee.java           # Employee entity / POJO
│   └── Main.java                   # Entry point — demo of all CRUD ops
└── pom.xml
```

---

## Deliverable 1 — Java Source Code (DAO Pattern)

### DAO Pattern Overview

| Layer | Class | Responsibility |
|---|---|---|
| Model | `Employee` | POJO with fields: id, name, email, department, designation, salary, phone, joiningDate |
| Config | `DatabaseConnection` | Returns a `java.sql.Connection` via `DriverManager` |
| DAO | `EmployeeDao` | Encapsulates all SQL — `createTable`, `insertRecord`, `getAllRecords`, `updateRecord`, `deleteRecord` |
| Entry Point | `Main` | Demonstrates all CRUD operations in sequence |

### Key Design Decisions

- `PreparedStatement` used for all DML to prevent SQL injection.
- Each DAO method opens and closes its own connection via try-with-resources — no leaked connections.
- `BigDecimal` for salary (exact decimal arithmetic).
- `LocalDate` / `java.sql.Date` conversion for `joining_date`.

---

## Deliverable 2 — SQL Schema

File: [`database/schema.sql`](database/schema.sql)

```sql
CREATE DATABASE IF NOT EXISTS employee_management;

USE employee_management;

CREATE TABLE IF NOT EXISTS employees (
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(100)    NOT NULL,
    email       VARCHAR(150)    NOT NULL UNIQUE,
    department  VARCHAR(100)    NOT NULL,
    designation VARCHAR(100),
    salary      DECIMAL(10,2),
    phone       VARCHAR(20),
    joining_date DATE
);
```

---

## Deliverable 3 — CRUD Operations

`Main.java` executes the following sequence on every run:

| Step | Operation | Details |
|---|---|---|
| 1 | CREATE TABLE | Creates `employees` table if it does not exist |
| 2 | INSERT | Inserts 3 employees (Rahul, Priya, Amit) |
| 3 | SELECT | Prints all employees |
| 4 | UPDATE | Updates Rahul's email, designation, and salary |
| 5 | SELECT | Prints all employees after update |
| 6 | DELETE | Deletes employee with id = 3 (Amit) |
| 7 | SELECT | Prints remaining employees after delete |

### Expected Console Output

```
========== CREATE TABLE ==========
Database connected successfully.
Employees table created successfully.

========== INSERT ==========
Database connected successfully.
Employee inserted successfully.
Database connected successfully.
Employee inserted successfully.
Database connected successfully.
Employee inserted successfully.

========== SELECT ==========
Database connected successfully.
Employee{id=1, name='Rahul Sharma', email='rahul@mail.com', department='IT', designation='Backend Developer', salary=65000.00, phone='9876543210', joiningDate=2025-06-10}
Employee{id=2, name='Priya Patel', email='priya@mail.com', department='HR', designation='HR Manager', salary=75000.00, phone='9876543211', joiningDate=2024-08-15}
Employee{id=3, name='Amit Shah', email='amit@mail.com', department='Finance', designation='Accountant', salary=55000.00, phone='9876543212', joiningDate=2023-03-20}

========== UPDATE ==========
Database connected successfully.
Employee updated successfully.

========== AFTER UPDATE ==========
Database connected successfully.
Employee{id=1, name='Rahul Sharma', email='rahul.sharma@mail.com', department='IT', designation='Senior Backend Developer', salary=85000.00, phone='9876543210', joiningDate=2025-06-10}
Employee{id=2, name='Priya Patel', email='priya@mail.com', department='HR', designation='HR Manager', salary=75000.00, phone='9876543211', joiningDate=2024-08-15}
Employee{id=3, name='Amit Shah', email='amit@mail.com', department='Finance', designation='Accountant', salary=55000.00, phone='9876543212', joiningDate=2023-03-20}

========== DELETE ==========
Database connected successfully.
Employee deleted successfully.

========== AFTER DELETE ==========
Database connected successfully.
Employee{id=1, name='Rahul Sharma', email='rahul.sharma@mail.com', department='IT', designation='Senior Backend Developer', salary=85000.00, phone='9876543210', joiningDate=2025-06-10}
Employee{id=2, name='Priya Patel', email='priya@mail.com', department='HR', designation='HR Manager', salary=75000.00, phone='9876543211', joiningDate=2024-08-15}
```

> Screenshots of the console output and MySQL Workbench results should be placed in a `screenshots/` folder.

---

## Deliverable 4 — Database Setup & Configuration

### Prerequisites

- Java 11+
- Maven 3.6+
- MySQL 8.x running locally

### Step 1 — Create the Database Schema

Open **MySQL Workbench** (or any MySQL client) and run:

```bash
mysql -u root -p < database/schema.sql
```

Or paste the contents of `database/schema.sql` directly into MySQL Workbench and execute.

### Step 2 — Configure the Database Connection

Edit [`src/main/java/org/employeemanagement/config/DatabaseConnection.java`](src/main/java/org/employeemanagement/config/DatabaseConnection.java):

```java
private static final String URL      = "jdbc:mysql://localhost:3306/employee_management";
private static final String USER     = "root";       // your MySQL username
private static final String PASSWORD = "";           // your MySQL password
```

> `DatabaseConnection.java` is listed in `.gitignore` via `db.properties` exclusion. If you prefer, move credentials to `src/main/resources/db.properties` and load them with `Properties`.

### Step 3 — Build & Run

```bash
# From the project root
mvn clean compile
mvn exec:java -Dexec.mainClass="org.employeemanagement.Main"
```

Or run `Main.java` directly from your IDE (IntelliJ IDEA / Eclipse / VS Code).

### Maven Dependency

The MySQL JDBC driver is declared in `pom.xml` — no manual JAR download needed:

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>9.4.0</version>
</dependency>
```

### Verify in MySQL Workbench

After running the application, execute in Workbench:

```sql
USE employee_management;
SELECT * FROM employees;
```

You should see 2 remaining rows (Rahul updated, Amit deleted).
