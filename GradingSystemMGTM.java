import java.util.Scanner;

class Student {
    private int studentID;
    private String name;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Student ID :" + studentID + " ,Student Name :" + name;
    }
}

class Grade {
    private int studentID;
    private int courseID;
    private char grade;

    public Grade(int studentID, int courseID, char grade) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public char getGrade() {
        return grade;
    }
    public String toString() {
        return "Student ID: " + studentID + ", Course ID: " + courseID + ", Grade: " + grade;
    }
}


class GradingSystem {
    private Student[] students;
    private Grade[] grades;
    private int[] courseCredits;
    private int studentCount;
    private int gradeCount;

    public GradingSystem(int numStudents, int numGrades, int numCourses) {
        students = new Student[numStudents];
        grades = new Grade[numGrades];
        courseCredits = new int[numCourses];
        studentCount = 0;
        gradeCount = 0;
    }

    public void addStudent(Student student) {
        if (studentCount < students.length) {
            students[studentCount++] = student;
            System.out.println("Added student " + student);
        } else {
            System.out.println("Maximum number of students reached.");
        }
    }

    public void addGrade(Grade grade) {
        if (gradeCount < grades.length) {
            grades[gradeCount++] = grade;
            
        } else {
            System.out.println("Maximum number of grades reached.");
        }
    }

    public void addCourseCredits(int courseID, int credits) {
        if (courseID < courseCredits.length) {
            courseCredits[courseID] = credits;
            System.out.println("Added course credits for course ID " + courseID + " with credits " + credits);
        } else {
            System.out.println("Invalid course ID!!!");
        }
    }

    public double calculateGPA(int studentID) {
        int totalPoints = 0;
        int totalCredits = 0;
        for (int i = 0; i < gradeCount; i++) {
            if (grades[i].getStudentID() == studentID) {
                totalPoints += gradeToPoints(grades[i].getGrade()) * courseCredits[grades[i].getCourseID()];
                totalCredits += courseCredits[grades[i].getCourseID()];
            }
        }
        if (totalCredits != 0) {
            return (double) totalPoints / totalCredits;
        } else {
            return 0.0;
        }
    }

    private int gradeToPoints(char grade) {
        switch (grade) {
            case 'A':
                return 4;
            case 'B':
                return 3;
            case 'C':
                return 2;
            case 'D':
                return 1;
            default:
                return 0;
        }
    }

    public void printGradeReport(int studentID) {
      System.out.println("Grade report for student ID " + studentID + ":");
      for (Grade grade : grades) {
          if (grade != null && grade.getStudentID() == studentID) {
              System.out.println(grade);
          }
      }
      System.out.println("GPA: " + calculateGPA(studentID));
  }
}

public class GradingSystemMGTM {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of students: ");
        int n = input.nextInt();
        System.out.println("Enter the number of grades: ");
        int g = input.nextInt();
        System.out.println("Enter the number of courses: ");
        int c = input.nextInt();
        GradingSystem gradingSystem = new GradingSystem(n, g, c);

        for (int i = 0; i < c; i++) {
            System.out.println("Enter course ID: ");
            int courseID = input.nextInt();
            System.out.println("Enter course credits: ");
            int credits = input.nextInt();
            gradingSystem.addCourseCredits(courseID, credits);
        }
        int choice;
        do {
            System.out.println("Enter 1: Add Student");
            System.out.println("Enter 2: Add Grade");
            System.out.println("Enter 3: Calculate GPA");
            System.out.println("Enter 4: Print Grade Report");
            System.out.println("Enter 5: Exit");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter student ID: ");
                    int id = input.nextInt();
                    System.out.println("Enter student name: ");
                    String name = input.next();
                    Student student = new Student(id, name);
                    gradingSystem.addStudent(student);
                    break;
                case 2:
                    System.out.println("Enter student ID: ");
                    int studentID = input.nextInt();
                    System.out.println("Enter course ID: ");
                    int courseID = input.nextInt();
                    System.out.println("Enter grade: ");
                    char grade = input.next().charAt(0);
                    Grade gradeObj = new Grade(studentID, courseID, grade);
                    gradingSystem.addGrade(gradeObj);
                    break;
                case 3:
                    System.out.println("Enter student ID: ");
                    int studentIDForGPA = input.nextInt();
                    double gpa = gradingSystem.calculateGPA(studentIDForGPA);
                    System.out.println("GPA of student ID " + studentIDForGPA + " is " + gpa);
                    break;
                case 4:
                    int stuid;
                    System.out.println("Enter Student ID to Print Grade Report : ");
                    stuid = input.nextInt();
                    gradingSystem.printGradeReport(stuid);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);
        input.close();
    }
}