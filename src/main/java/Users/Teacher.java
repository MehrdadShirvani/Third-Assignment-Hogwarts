package Users;

import Courses.Course;
import Courses.StudentCourseDetail;
import Hogwarts.Hogwarts;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Teacher extends User
{
    public Teacher(String firstName, String lastName, String username, String password, int houseId) throws Exception {
        super(firstName, lastName, username, password);
        this.houseID = houseId;
        this.roleID = 2; // Teacher's Role Id
    }

    public static Teacher getTeacher(UUID teacherID)
    {
        List<Teacher> teachers = Hogwarts.getAllTeachers();
        for(Teacher teacher : teachers)
        {
            if(teacher.userID == teacherID)
            {
                return teacher;
            }
        }
        return null;
    }

    public void takeCourse(Course course)
    {
        course.setTeacherId(userID);
    }

    public List<Student> getUnscoredStudents()
    {
        return StudentCourseDetail.getStudentsOfTeacher(userID, true);
    }

    public void scoreStudent(StudentCourseDetail detail, double grade)
    {
        detail.setGrade(grade);
    }
    public List<Course> getCourses()
    {
        List<Course> courses = Hogwarts.getAllCourses();
        List<Course> result = new ArrayList<>();
        for(Course course : courses)
        {
            if(course.getTeacherId() == userID)
            {
                result.add(course);
            }
        }
        return result;
    }

    public double getScore()
    {
        return 1;
        //TODO
    }
}
