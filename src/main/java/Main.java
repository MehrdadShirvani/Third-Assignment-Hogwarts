import Courses.Course;
import Courses.StudentCourseDetail;
import Hogwarts.Hogwarts;
import Users.*;
import Utilities.ColorPrinter;

import java.util.*;

public class Main {
    private static User currentUser;
    public static void main(String[] args)
    {
        while(runMenu());
    }
    //Basic Menu -----------------------------------------------------------------------------
    public static boolean runMenu()
    {
        System.out.println();
        System.out.println(" ---Hogwarts School of Witchcraft and Wizardry--- ");
        System.out.println("|                                                |");
        System.out.println("|                    welcome                     | ");
        System.out.println("|                                                |");
        System.out.println("|         Enter the number of the command        | ");
        System.out.println("|              and press enter                   |");
        System.out.println("|                                                |");
        System.out.println("|                 1) Login                       |");
        System.out.println("|                 2) Sign up                     |");
        System.out.println("|                 3) Exit                        |");
        System.out.println("|                                                |");
        System.out.println(" ------------------------------------------------ ");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();

        switch (command) {
            case "1" -> {
                showLoginPage();
                return true;
            }
            case "2" -> {
                showSignupPage();
                return true;
            }
            case "3" -> {
                return false;
            }
        }

        return true;
    }
    private static void showSignupPage()
    {
        System.out.println();
        System.out.println(" ---Hogwarts School of Witchcraft and Wizardry--- ");
        System.out.println("|                                                |");
        System.out.println("|                    Sign Up                     | ");
        System.out.println("|                                                |");
        System.out.println("|          Enter your username and password      | ");
        System.out.println("|          Enter your firstname and lastname     | ");
        System.out.println("|               Enter your house name            | ");
        System.out.println("|        Enter your role (teacher/student)       | ");
        System.out.println("|   press enter after entering each one of them  |");
        System.out.println("|          enter EXIT as username to exit        |");
        System.out.println("|                                                |");
        System.out.println(" ------------------------------------------------ ");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        ColorPrinter.printBlue("Enter Username: ");
        String username = scanner.next();
        if(Objects.equals(username, "EXIT"))
        {
            return;
        }
        ColorPrinter.printBlue("Enter Password: ");
        String password = scanner.next();

        ColorPrinter.printBlue("Enter First Name: ");
        String firstName = scanner.next();

        ColorPrinter.printBlue("Enter Last Name: ");
        String lastName = scanner.next();

        ColorPrinter.printBlue("Enter Your House Number:  ");
        ColorPrinter.printBlue("1)Gryffindor\n2)Hufflepuff\n3)Ravenclaw\n4)Slytherin");
        int houseId = scanner.nextInt();
        if(houseId < 1) houseId = 1;
        if(houseId > 4) houseId = 4;

        ColorPrinter.printBlue("Enter Your Role Id:  ");
        ColorPrinter.printBlue("1)Teacher\n2)Student");
        int roleId = scanner.nextInt();
        if(roleId < 1) roleId = 1;
        if(roleId > 2) roleId = 2;
        roleId++;

        try
        {
            User newUser;
            if(roleId == 2)
            {
                newUser = new Teacher(firstName, lastName, username,password, houseId );
            }
            else
            {
                newUser = new Student(firstName, lastName, username,password, houseId );
            }

            Hogwarts.addUnconfirmedUser(newUser);

            ColorPrinter.printGreen("You are on the waiting list for confirmation");
            Thread.sleep(2000);
        }
        catch(Exception ignored)
        {

        }
    }
    private static void showLoginPage()
    {
        System.out.println();
        System.out.println(" ---Hogwarts School of Witchcraft and Wizardry--- ");
        System.out.println("|                                                |");
        System.out.println("|                    welcome                     | ");
        System.out.println("|                                                |");
        System.out.println("|        Enter the your username and password    | ");
        System.out.println("|   press enter after entering each one of them  |");
        System.out.println("|          enter EXIT as username to exit        |");
        System.out.println("|                                                |");
        System.out.println(" ------------------------------------------------ ");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        ColorPrinter.printGreen("Enter Username: ");
        String username = scanner.next();
        if(Objects.equals(username, "EXIT"))
        {
            return;
        }
        ColorPrinter.printGreen("Enter Password: ");
        String password = scanner.next();

        currentUser = UserManagement.login(username, password);
        if(currentUser == null)
        {
            System.out.println();
            ColorPrinter.printRed("WRONG CREDENTIALS or UNCONFIRMED USER");
            ColorPrinter.printRed("Please Enter Again");
            try
            {
                Thread.sleep(2000);
            }
            catch (Exception ignored)
            {

            }
            showLoginPage();
            return;
        }

        printWelcome();
        if(currentUser instanceof Student)
        {
            showUserMenu();
            return;
        }

        if (currentUser instanceof Teacher)
        {
            showTeacherMenu();
            return;
        }

        if(currentUser instanceof Assistant)
        {
            showAssistantMenu();
            return;
        }
    }
    private static void printWelcome() {
        System.out.println();
        System.out.println();
        ColorPrinter.printYellow("Welcome " + currentUser.getFullName());
        ColorPrinter.printGreen("(" + currentUser.getRoleName() + ")");

        try
        {
            Thread.sleep(3000);
        }
        catch (Exception ignored)
        {

        }
    }

    //Assistant Menu -----------------------------------------------------------------------------
    private static void showAssistantMenu()
    {
        System.out.println();
        System.out.println("-------- Enter the command number --------");
        System.out.println("1) Confirm users");
        System.out.println("2) Add new assistant");
        System.out.println("3) View courses and their students");
        System.out.println("4) View(Remove) student or teacher");
        System.out.println("5) Create new course");
        System.out.println("6) Logout");

        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        switch (command)
        {
            case 1: showConfirmUser(); break;
            case 2: showAddNewAssistant(); break;
            case 3: showCourses(); break;
            case 4: showStudentsAndTeachers(); break;
            case 5: showAddNewCourse(); break;
            default: currentUser = null; return;
        }

        showAssistantMenu();
    }
    private static void showAddNewCourse()
    {
        System.out.println();
        System.out.println(" ---Hogwarts School of Witchcraft and Wizardry--- ");
        System.out.println("|                                                |");
        System.out.println("|                     New Course                 | ");
        System.out.println("|                                                |");
        System.out.println("|               Enter the  Course name           | ");
        System.out.println("|              Enter EXIT as name to exit        |");
        System.out.println("|                                                |");
        System.out.println(" ------------------------------------------------ ");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        ColorPrinter.printBlue("Enter Name: ");
        String name = scanner.nextLine();
        if(Objects.equals(name, "EXIT"))
        {
            return;
        }

        Course course = new Course(name);
        Hogwarts.addCourse(course);
        ColorPrinter.printGreen("Course was added successfully");
        try
        {
            Thread.sleep(2000);
        }
        catch (Exception ignored)
        {

        }
    }
    private static void showStudentsAndTeachers()
    {
        List<User> users = Hogwarts.getAllStudentsAndTeachers();

        if(users.isEmpty())
        {
            ColorPrinter.printGreen("No Teacher or Student...");
            try
            {
                Thread.sleep(2000);
                return;
            }
            catch (Exception ignored)
            {

            }
        }
        System.out.println();
        int i = 1;
        for(User user : users)
        {
            if(user instanceof Student) {
                ColorPrinter.printGreen(i + ") " + user.getFullName() + " (" + user.getRoleName()+")");
            }
            else {
                ColorPrinter.printBlue(i + ") " + user.getFullName() + " (" + user.getRoleName()+")");
            }
            i++;
        }

        System.out.println("Enter number: ");
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        if(command > users.size() || command < 1)
        {
            return;
        }

        viewProfile(users.get(command - 1));
    }
    private static void viewProfile(User user)
    {
        System.out.println();
        System.out.println("-------- About User --------");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Full Name: " + user.getFullName());
        System.out.println("Role: " + user.getRoleName());
        if(user instanceof Teacher)
            System.out.println("Score by Students: " + ((Teacher)user).getAverageScore());
        System.out.println("House: " + user.getHouseName());

        ColorPrinter.printYellow("Enter Command:\n1)Remove User \n2)Get Back");
        int command = (new Scanner(System.in)).nextInt();
        if(command == 1)
        {
            ((Assistant)currentUser).removeUser(user);
            ColorPrinter.printGreen("User was deleted...");
            try
            {
                Thread.sleep(2000);
            }
            catch (Exception ignored)
            {

            }
        }
    }
    private static void showCourses()
    {
        System.out.println();
        System.out.println("-------- Enter the course number --------");
        List<Course> courses = Hogwarts.getAllCourses();
        if(courses.isEmpty())
        {
            ColorPrinter.printRed("No Course To Show...");
            try
            {
                Thread.sleep(2000);
                return;
            }
            catch (Exception ignored)
            {

            }
        }
        int i = 1;
        for(Course course : courses)
        {
            System.out.println(i + ") " + course.getName());
            i++;
        }

               ColorPrinter.printYellow("Enter Course Number: ");
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        if(command > courses.size() || command < 1)
        {
            return;
        }
        Course course = courses.get(command - 1);
        showStudentsOfCourse(course, false);
    }
    private static void showStudentsOfCourse(Course course, boolean isTeacher)
    {
        List<Student> studentsOfCourse = course.getStudents();
        if(studentsOfCourse.isEmpty())
        {
            ColorPrinter.printRed("No Student Enrolled...");
            try
            {
                Thread.sleep(2000);

                return;
            }
            catch (Exception ignored)
            {

            }
        }
        int i = 1;

        //Soring Students
        Collections.sort(studentsOfCourse, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                // compare two instance of `Score` and return `int` as result.
                return s2.getFullName().compareTo(s1.getFullName());
            }
        });

        for(Student student : studentsOfCourse)
        {
            System.out.println(i + ") " + student.getFullName());
            i++;
        }
        if(isTeacher)
        {
            System.out.println("Enter student number to score: ");
            Scanner scanner = new Scanner(System.in);
            int command = scanner.nextInt();
            if(command < 1) command = 1;
            if(command > studentsOfCourse.size()) command = studentsOfCourse.size();
            System.out.println("Enter grade: ");
            double grade = scanner.nextDouble();

            Student student = studentsOfCourse.get(command - 1);

            StudentCourseDetail detail = StudentCourseDetail.getStudentCourseDetail(student.getUserID(), course.getCourseID());
            detail.setGrade(grade);

            ColorPrinter.printGreen("Grade was set...");
            try
            {
                Thread.sleep(2000);
                return;
            }
            catch (Exception ignored)
            {

            }
            return;
        }
        else
        {
            System.out.println("Enter anything to get back");
            Scanner scanner = new Scanner(System.in);
            scanner.next();
        }

    }

    private static void showAddNewAssistant()
    {
        System.out.println();
        System.out.println(" ---Hogwarts School of Witchcraft and Wizardry--- ");
        System.out.println("|                                                |");
        System.out.println("|                    Sign Up                     | ");
        System.out.println("|                                                |");
        System.out.println("|          Enter the  username and password      | ");
        System.out.println("|          Enter the  firstname and lastname     | ");
        System.out.println("|   press enter after entering each one of them  |");
        System.out.println("|          enter EXIT as username to exit        |");
        System.out.println("|                                                |");
        System.out.println(" ------------------------------------------------ ");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        ColorPrinter.printBlue("Enter Username: ");
        String username = scanner.next();
        if(Objects.equals(username, "EXIT"))
        {
            return;
        }
        ColorPrinter.printBlue("Enter Password: ");
        String password = scanner.next();

        ColorPrinter.printBlue("Enter First Name: ");
        String firstName = scanner.next();

        ColorPrinter.printBlue("Enter Last Name: ");
        String lastName = scanner.next();

        try
        {
            Assistant newUser = new Assistant(firstName, lastName, username, password);
            ((Assistant)currentUser).addNewAssistant(newUser);

            ColorPrinter.printGreen("admin was added successfully");
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            ColorPrinter.printRed("something went wrong!");
        }

    }

    private static void showConfirmUser()
    {
        System.out.println();
        System.out.println("-------- Enter the user number --------");
        List<User> unconfirmedUsers = Hogwarts.getUnconfirmedUsers();
        if(unconfirmedUsers.isEmpty())
        {
            ColorPrinter.printGreen("No Unconfirmed User...");
            try
            {
                Thread.sleep(2000);
                return;
            }
            catch (Exception ignored)
            {

            }
        }
        int i = 1;
        for(User user : unconfirmedUsers)
        {
            System.out.println(i + ") " + user.getFullName() + " (" + user.getRoleName()+")");
        }

        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        if(command > unconfirmedUsers.size() || command < 1)
        {
            return;
        }

        User newUser = unconfirmedUsers.get(command - 1);
        Hogwarts.addUser(newUser);
        unconfirmedUsers.remove(command - 1);

        ColorPrinter.printGreen("User was confirmed!");
        try
        {
            Thread.sleep(2000);
            return;
        }
        catch (Exception ignored)
        {

        }
    }

    //Teacher Menu -----------------------------------------------------------------------------

    private static void showTeacherMenu()
    {
        System.out.println();
        double averageScore = ((Teacher)currentUser).getAverageScore();
        ColorPrinter.printBlue("Teacher's Average Score: " + (averageScore == 0?"no score submitted":averageScore));
        System.out.println();
        System.out.println("-------- Enter the command number --------");
        System.out.println("1) Take Course");
        System.out.println("2) View Current Courses and Students");
        System.out.println("3) View your score and comments");
        System.out.println("4) Logout");

        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        switch (command)
        {
            case 1: showCoursesToTake(); break;
            case 2: showTeacherCurrentCourses(); break;
            case 3: showCommentsAndScores(); break;
            default: currentUser = null; return;
        }
        showTeacherMenu();
    }

    private static void showCommentsAndScores()
    {
        double averageScore = ((Teacher)currentUser).getAverageScore();
        ColorPrinter.printBlue("Teacher's Average Score: " + (averageScore == 0?"no score submitted":averageScore));

        List<String> comments = ((Teacher)currentUser).getComments();
        if(comments.isEmpty())
        {
            ColorPrinter.printRed("No Comment To Show...");
            try
            {
                Thread.sleep(2000);
                return;
            }
            catch (Exception ignored)
            {

            }
        }

        int i = 1;
        for(String comment : comments)
        {
            System.out.println(i + ") " + "\""+comment + "\"");
        }

        System.out.println("Enter anything to get back");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }

    private static void showTeacherCurrentCourses()
    {
        System.out.println();
        System.out.println("-------- Enter the course number --------");
        List<Course> courses = ((Teacher)currentUser).getCourses();
        if(courses.isEmpty())
        {
            ColorPrinter.printRed("No Course To Show...");
            try
            {
                Thread.sleep(2000);
                return;
            }
            catch (Exception ignored)
            {

            }
        }
        int i = 1;
        for(Course course : courses)
        {
            System.out.println(i + ") " + course.getName());
            i++;
        }

               ColorPrinter.printYellow("Enter Course Number: ");
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        if(command > courses.size() || command < 1)
        {
            return;
        }
        Course course = courses.get(command - 1);
        showStudentsOfCourse(course, true);
    }

    private static void showCoursesToTake() 
    {
        System.out.println();
        System.out.println("-------- Enter the course number --------");
        List<Course> courses = Course.getCoursesWithNoTeacher();
        if(courses.isEmpty())
        {
            ColorPrinter.printRed("No Course To Show(All the others are already taken)...");
            try
            {
                Thread.sleep(2000);
                return;
            }
            catch (Exception ignored)
            {

            }
        }
        int i = 1;
        for(Course course : courses)
        {
            System.out.println(i + ") " + course.getName());
            i++;
        }

        ColorPrinter.printYellow("Enter Course Number: ");
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        if(command > courses.size() || command < 1)
        {
            return;
        }
        Course course = courses.get(command - 1);
        ((Teacher)currentUser).takeCourse(course);

        ColorPrinter.printGreen("You have taken this course...");
        try
        {
            Thread.sleep(2000);
            return;
        }
        catch (Exception ignored)
        {

        }
    }
    //User Menu -----------------------------------------------------------------------------

    private static void showUserMenu()
    {
        System.out.println();
        System.out.println("-------- Enter the command number --------");
        System.out.println("1) Enroll in course");
        System.out.println("2) View Current Courses And Grades");
        System.out.println("3) View Current Teachers");
        System.out.println("4) Logout");

        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        switch (command)
        {
            case 1: showCoursesToEnroll(); break;
            case 2: showStudentCurrentCourses(); break;
            case 3: showStudentAllTeachers(); break;
            default: currentUser = null; return;
        }

        showUserMenu();
    }
    private static void showCoursesToEnroll()
    {
        List<Course> studentCourses = ((Student) currentUser).getCourses();
        List<Course> allCoursesWithTeacher = Hogwarts.getAllCoursesWithTeacher();
        List<Course> notEnrolledCourses = new ArrayList<>(allCoursesWithTeacher);
        notEnrolledCourses.removeAll(studentCourses);
        if(notEnrolledCourses.isEmpty())
        {
            ColorPrinter.printRed("No course to show (there is no course with a teacher that you have not enrolled in...)");
            try
            {
                Thread.sleep(2000);

                return;
            }
            catch (Exception ignored)
            {

            }
        }


        int i = 1;
        for(Course course : notEnrolledCourses)
        {
            System.out.println(i + ") " + course.getName());
            i++;
        }

        ColorPrinter.printYellow("Enter Course Number: ");
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        if(command > notEnrolledCourses.size() || command < 1)
        {
            return;
        }
        Course course = notEnrolledCourses.get(command - 1);
        ((Student)currentUser).enrollInCourse(course);
        ColorPrinter.printGreen("Successfully Enrolled in Course");
        try
        {
            Thread.sleep(2000);
            return;
        }
        catch (Exception ignored)
        {

        }
    }
    private static void showStudentAllTeachers()
    {
        List<Teacher> teachersOfStudent = StudentCourseDetail.getTeachersOfStudent(currentUser.getUserID());
        if(teachersOfStudent.isEmpty())
        {
            ColorPrinter.printRed("No teacher to show (no course enrolled)...");
            try
            {
                Thread.sleep(2000);

                return;
            }
            catch (Exception ignored)
            {

            }
        }
        System.out.println("---- Current Teachers ----");
        int i = 1;
        for(Teacher teacher : teachersOfStudent)
        {
            System.out.println(i + ") " + teacher.getFullName());
            i++;
        }
        System.out.println();
        System.out.println("Enter anything to get back (You can put comment for them from other options of the menu)");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }

    private static void showStudentCurrentCourses()
    {
        System.out.println();
        System.out.println("-------- Enter the course number --------");
        List<Course> courses = ((Student) currentUser).getCourses();
        if(courses.isEmpty())
        {
            ColorPrinter.printRed("No Course To Show...");
            try
            {
                Thread.sleep(2000);
                return;
            }
            catch (Exception ignored)
            {

            }
        }
        int i = 1;
        for(Course course : courses)
        {
            System.out.println(i + ") " + course.getName());
            i++;
        }

        ColorPrinter.printYellow("Enter Course Number: ");
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        if(command > courses.size() || command < 1)
        {
            return;
        }
        Course course = courses.get(command - 1);
        StudentCourseDetail detail =  StudentCourseDetail.getStudentCourseDetail(((Student) currentUser).getUserID(), course.getCourseID());
        showStudentCourseDetail(detail);
    }
    private static void showStudentCourseDetail(StudentCourseDetail detail)
    {
        System.out.println("-------- Student Course Detail --------");
        System.out.println("Grade: " + detail.getGradeString());
        System.out.println("Course Name: " + detail.getCourseName());
        System.out.println("Teacher Name: " + detail.getTeacherName());

        Scanner scanner = new Scanner(System.in);
        ColorPrinter.printYellow("Score Teacher: (1-5)");
        double teacherScore = scanner.nextDouble();
        if(teacherScore < 1) teacherScore = 1;
        if(teacherScore > 5) teacherScore = 5;

        detail.setTeacherGrade(teacherScore);
        ColorPrinter.printYellow("Enter Comment for teacher: ");
        scanner = new Scanner(System.in);
        String comment = scanner.nextLine();
        detail.setTeacherComment(comment);

        ColorPrinter.printGreen("Successfully scored and commented teacher");
        try
        {
            Thread.sleep(2000);
            return;
        }
        catch (Exception ignored)
        {

        }
    }
}
