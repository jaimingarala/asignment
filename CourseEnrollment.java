import java.util.Scanner;

class Course {
    private int courseID;
    private String courseName;
    private int credits;

    public Course(int courseID, String courseName, int credits) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credits = credits;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseID + ", Course Name: " + courseName + ", Credits: " + credits;
    }
}

class Enrollment {
    private int[][] studentCourses;
    private int[] count;

    public Enrollment(int numStudents, int numCourses) {
        studentCourses = new int[numStudents][numCourses];
        count = new int[numStudents];
    }

    public void enroll(int studentID, int courseID) {
        if (studentID < studentCourses.length && count[studentID] < studentCourses[studentID].length) {
            studentCourses[studentID][count[studentID]] = courseID;
            count[studentID]++;
            System.out.println("Student ID No. " + studentID + " enrolled in course ID " + courseID);
        } else {
            System.out.println("Invalid student ID or course limit reached.");
        }
    }

    public void drop(int studentID, int courseID) {
        if (studentID < studentCourses.length) {
            for (int i = 0; i < count[studentID]; i++) {
                if (studentCourses[studentID][i] == courseID) {
                    studentCourses[studentID][i] = studentCourses[studentID][--count[studentID]];
                    System.out.println("Student ID No. " + studentID + " dropped course ID " + courseID);
                    return;
                }
            }
            System.out.println("Course ID " + courseID + " not found for student " + studentID);
        } else {
            System.out.println("Invalid student ID.");
        }
    }

    public void getEnrolledCourses(int studentID, Course[] courseCatalog) {
        if (studentID < studentCourses.length) {
            System.out.println("Courses enrolled by student ID " + studentID + ":");
            for (int i = 0; i < count[studentID]; i++) {
                int courseID = studentCourses[studentID][i];
                for (Course course : courseCatalog) {
                    if (course.getCourseID() == courseID) {
                        System.out.println(course);
                        break;
                    }
                }
            }
        } else {
            System.out.println("Invalid student ID.");
        }
    }
}

public class CourseEnrollment {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;
        System.out.print("Enter Number of Courses: ");
        int courseNum = input.nextInt();
        System.out.print("Enter Number of Students: ");
        int studentNum = input.nextInt();
        Enrollment enrollment = new Enrollment(studentNum, courseNum);
        Course[] courses = new Course[courseNum];

        for (int i = 0; i < courseNum; i++) {
            System.out.print("Enter Course ID: ");
            int courseID = input.nextInt();
            System.out.print("Enter Course Name: ");
            String courseName = input.next();
            System.out.print("Enter Course Credits: ");
            int credits = input.nextInt();
            courses[i] = new Course(courseID, courseName, credits);
        }

        do {
            System.out.println("Enter 1: Enroll Course.");
            System.out.println("Enter 2: Drop Course.");
            System.out.println("Enter 3: View Enrolled Courses.");
            System.out.println("Enter 4: Exit.");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int studentID = input.nextInt();
                    System.out.print("Enter Course ID: ");
                    int courseID = input.nextInt();
                    enrollment.enroll(studentID, courseID);
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    studentID = input.nextInt();
                    System.out.print("Enter Course ID: ");
                    courseID = input.nextInt();
                    enrollment.drop(studentID, courseID);
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentID = input.nextInt();
                    enrollment.getEnrolledCourses(studentID, courses);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);

        input.close();
    }
}
