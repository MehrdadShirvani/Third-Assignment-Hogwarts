package Hogwarts;

import Courses.Course;
import Courses.StudentCourseDetail;
import Users.Student;
import Users.Teacher;
import Users.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hogwarts {

    private static List<User> users = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();
    private static List<StudentCourseDetail> studentCourseDetails = new ArrayList<>();

    static
    {
        users = new ArrayList<>();
        try
        {
            users.add(new Teacher("Remus","Lupin", "Moony", "Chocolate", 1));
            users.add(new Student("Harry","Potter", "TheBoyWhoLived", "Expelliarmus", 1));
        }
        catch (Exception exception)
        {

        }
    }

//    public List<Administrator> getAllTeachers()
//    {
//        //TODO
//    }

    public static List<Teacher> getAllTeachers()
    {
        List<Teacher> teachers = new ArrayList<>();
        for(User user : users)
        {
            if(user instanceof Teacher)
            {
                teachers.add((Teacher) user);
            }
        }
        return teachers;
    }

    public static List<Student> getAllStudents()
    {
        List<Student> students = new ArrayList<>();
        for(User user : users)
        {
            if(user instanceof Student)
            {
                students.add((Student) user);
            }
        }
        return students;
    }

    public static List<Course> getAllCourses()
    {
        return courses;
    }

    public static List<StudentCourseDetail> getAllStudentCourseDetails()
    {
        return studentCourseDetails;
    }

    public static void addStudentCourseDetail(StudentCourseDetail detail)
    {
        studentCourseDetails.add(detail);
    }

    public static void addUser(User user)
    {
        users.add(user);
    }

    public static List<User> getAllUsers()
    {
        return Collections.unmodifiableList(users);
    }

    public static void removeUser(User user)
    {
        users.remove(user);
    }

    public static void addCourse(Course course)
    {
        courses.add(course);
    }
}
