package Courses;

import Hogwarts.Hogwarts;
import Users.Student;
import Users.Teacher;

import java.util.*;

public class StudentCourseDetail
{
    //region Fields
    private UUID studentCourseDetailID;
    private UUID courseID;
    private UUID studentID;
    private double grade;
    private double teacherScore;
    private boolean hasBeenGraded;
    private String studentComment;
    //endregion

    public StudentCourseDetail(UUID studentID, UUID courseID)
    {
        this.studentCourseDetailID = UUID.randomUUID();
        this.studentID = studentID;
        this.courseID = courseID;
    }

    public static List<Student> getStudentsOfCourse(UUID courseID)
    {
        List<Student> result = new ArrayList<>();
        List<StudentCourseDetail> details = Hogwarts.getAllStudentCourseDetails();
        for(StudentCourseDetail detail : details)
        {
            if(detail.courseID == courseID)
            {
                Student student = Student.getStudent(detail.studentID);
                if(result.contains(student) == false)
                {
                    result.add(student);
                }
            }
        }
        return result;
    }
    public static List<Student> getStudentsOfTeacher(UUID teacherID, boolean onlyUnscoredOnes)
    {
        List<Student> result = new ArrayList<>();
        List<StudentCourseDetail> details = Hogwarts.getAllStudentCourseDetails();
        for(StudentCourseDetail detail : details)
        {
            if(onlyUnscoredOnes && detail.hasBeenGraded == false)
            {
                continue;
            }

            Course course = Course.getCourse(detail.courseID);
            if(course.teacherID == teacherID)
            {
                Student student = Student.getStudent(detail.studentID);
                if(result.contains(student) == false)
                {
                    result.add(student);
                }
            }
        }
        return result;
    }
    public static List<Teacher> getTeachersOfStudent(UUID studentID)
    {
        List<Teacher> result = new ArrayList<>();
        List<StudentCourseDetail> details = Hogwarts.getAllStudentCourseDetails();
        for(StudentCourseDetail detail : details)
        {
            if(detail.studentID == studentID)
            {
                Course course = Course.getCourse(detail.courseID);
                Teacher teacher = Teacher.getTeacher(course.teacherID);
                if(result.contains(teacher) == false)
                {
                    result.add(teacher);
                }
            }
        }
        return result;
    }

    public static List<Course> getCoursesOfStudent(UUID studentID)
    {
        List<Course> result = new ArrayList<>();
        List<StudentCourseDetail> details = Hogwarts.getAllStudentCourseDetails();
        for(StudentCourseDetail detail : details)
        {
            if(detail.studentID == studentID)
            {
                Course course = Course.getCourse(detail.courseID);
                if(result.contains(course) == false)
                {
                    result.add(course);
                }
            }
        }
        return result;
    }

    public void setGrade(double grade)
    {
        if(grade < 0) grade = 0;
        if(grade > 20) grade = 20;
        this.grade = grade;
        this.hasBeenGraded = true;
    }
}
