package Users;

public class Student extends User
{

    public Student(String firstName, String lastName, String username, String password, int houseID) throws Exception {
        super(firstName, lastName, username, password);
        this.houseID = houseID;
        this.roleID = 3; // Student Role Id
    }
}
