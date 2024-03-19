package Courses;

import Hogwarts.Hogwarts;
import Users.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Course
{
    //region Fields
    protected UUID courseID;
    protected String name;
    protected UUID teacherID;
    protected boolean hasTeacher = false;
    //endregion

    public static List<Course> getCoursesWithNoTeacher()
    {
        List<Course> courses = Hogwarts.getAllCourses();
        List<Course> result = new ArrayList<>();
        for(Course course : courses)
        {
            if(course.hasTeacher == false)
            {
                result.add(course);
            }
        }
        return result;
    }

    public List<Student> getStudents()
    {
        return StudentCourseDetail.getStudentsOfCourse(courseID);
    }

    public static Course getCourse(UUID courseID)
    {
        List<Course> courses = Hogwarts.getAllCourses();
        for(Course course : courses)
        {
            if(course.courseID == courseID)
            {
                return course;
            }
        }
        return null;
    }

    public void setTeacherId(UUID teacherID)
    {
        this.teacherID = teacherID;
        this.hasTeacher = true;
    }

    public UUID getCourseID()
    {
        return courseID;
    }

    public UUID getTeacherId()
    {
        return teacherID;
    }
}
