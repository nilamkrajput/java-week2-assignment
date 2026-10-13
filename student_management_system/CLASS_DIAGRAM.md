# UML Class Diagram — Student Management System

```
+-------------------+          +----------------------+
|      Student      |          |   StudentManager     |
+-------------------+          +----------------------+
| - id : int        |<>--------| - students : List    |
| - name : String   |  0..*    +----------------------+
| - age : int       |          | + addStudent()       |
| - grade : double  |          | + findById()         |
| - subjects : List |          | + findByName()       |
+-------------------+          | + updateStudent()    |
| + getId()         |          | + deleteStudent()    |
| + getName()       |          | + getAllStudents()    |
| + setName()       |          | + calculateAverage() |
| + getAge()        |          +----------+-----------+
| + setAge()        |                     |
| + getGrade()      |                     | uses
| + setGrade()      |                     |
| + getSubjects()   |          +----------v-----------+
| + setSubjects()   |          |        Main          |
+-------------------+          +----------------------+
         ^                     | - scanner            |
         |                     | - studentManager     |
         | serializes          +----------------------+
         |                     | + main()             |
+--------+-----------+         | + addStudent()       |
|    FileHandler     |<--------| + displayAll()       |
+--------------------+  uses   | + searchStudent()    |
| - FILE_PATH        |         | + updateStudent()    |
+--------------------+         | + deleteStudent()    |
| + saveStudents()   |         | + calculateAverage() |
| + loadStudents()   |         | + saveData()         |
+--------------------+         | + loadData()         |
                               +----------------------+

  <>----  Aggregation
  <----   Dependency (uses / serializes)
```
