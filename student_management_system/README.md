# Student Management System

A console-based Java application to manage student records with file persistence.

---

## Project Structure

```
student_management_system/
├── data/
│   └── students.dat          # Serialized student data (auto-created)
├── src/
│   └── com/
│       └── studentmanagement/
│           ├── model/
│           │   └── Student.java       # Student entity
│           ├── service/
│           │   └── StudentManager.java # Business logic
│           ├── util/
│           │   └── FileHandler.java   # File persistence
│           └── Main.java              # Entry point & UI
├── README.md
└── CLASS_DIAGRAM.md
```

---

## Prerequisites

- Java JDK 8 or higher
- Command Prompt / Terminal

---

## Compilation

From the `student_management_system/` root directory:

```bash
javac -d out -sourcepath src src/com/studentmanagement/Main.java
```

This compiles all source files into an `out/` directory.

---

## Running

```bash
java -cp out com.studentmanagement.Main
```

---

## Features

| Option | Operation              |
|--------|------------------------|
| 1      | Add Student            |
| 2      | Display All Students   |
| 3      | Search Student (ID/Name) |
| 4      | Update Student         |
| 5      | Delete Student         |
| 6      | Calculate Class Average |
| 7      | Save Data to File      |
| 8      | Save & Exit            |

---

## Sample Console Output

```
======================================
       STUDENT MANAGEMENT SYSTEM
======================================
1. Add Student
2. Display All Students
3. Search Student
4. Update Student
5. Delete Student
6. Calculate Class Average
7. Save Data
8. Exit
======================================
Enter your choice :1
-------- Add Student
Enter Student ID : 1
Enter Name : heta
Enter age : 12
Enter grade : 50
Enter subjects separated by comma :
maths, physics, chemistry
Student added successfully

======================================
Enter your choice :2

ID       Name                 Age      Grade      Subjects
----------------------------------------------------------------------
1        heta                 12       50.00      maths, physics, chemistry
2        amit                 24       60.00      chemistry, physics, maths

Enter your choice :3

--- Search Student ---
1. Search by ID
2. Search by Name
Choose Option : 1
Enter Student ID to search : 2

ID       Name                 Age      Grade      Subjects
----------------------------------------------------------------------
2        amit                 24       60.00      chemistry, physics, maths

Enter your choice :4

--- Update Student ---
Enter student ID: 1
Press Enter to keep the existing value.
Name [heta]: hetu
Age [12]:
Grade [50.0]:
Current subjects: maths, physics, chemistry
Enter new subjects separated by comma (Enter to keep existing):
Student updated successfully.

Enter your choice :6
Class Average Grade: 55.00

Enter your choice :5

 ------ Delete Student ------
Enter student ID : 2
Are you sure you want to delete? (Y/N) :
y
Student deleted successfully.

Enter your choice :8
Data saved successfully
Thank you for using the system
```

---

## Notes

- Student data is saved to `data/students.dat` using Java serialization.
- Data is automatically loaded on startup and saved on exit (option 8) or manually (option 7).
