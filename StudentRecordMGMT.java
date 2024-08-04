import java.util.*;

class student {
    private int studentID;
    private String name;
    private int age;
    private String department;

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return  "studentID=" + studentID +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", department='" + department + '\'';
    }
}

class StudentRecordSystem extends student{
    private student[] students = new student[10];
    private int count;

    public void addStudent(student Student) {
        if (count < students.length) {
            students[count] = Student;
            count++;
        } else {
            System.out.println("Cannot add more students. Array is full.");
        }
    }

    public student getStudent(int id) {
        for (student student : students) {
            if (student!= null && student.getStudentID() == id) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (student student : students) {
            if (student!= null) {
                System.out.println(student);
            }
        }
    }
}

public class StudentRecordMGMT {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StudentRecordSystem src = new StudentRecordSystem();
        int s;

        do{
            System.out.println("\n1. Add Student\n2. Get Student\n3. Display All Students");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    student student = new student();
                    System.out.print("Enter student ID: ");
                    student.setStudentID(sc.nextInt());
                    System.out.print("Enter name: ");
                    student.setName(sc.next());
                    System.out.print("Enter age: ");
                    student.setAge(sc.nextInt());
                    System.out.print("Enter department: ");
                    student.setDepartment(sc.next());
                    src.addStudent(student);
                    break;

                case 2:
                    System.out.print("Enter student ID: ");
                    int id = sc.nextInt();
                    student = src.getStudent(id);
                    if (student!= null) {
                        System.out.println(student);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    src.displayAllStudents();
                    break;
                
                default:
                    System.out.println("please enter valid option.");
            }
            System.out.print("Do you want to continue (y/n)? ");
            s = sc.next().charAt(0);
        }while(s == 'y' || s == 'Y');

        sc.close();
    }
}
