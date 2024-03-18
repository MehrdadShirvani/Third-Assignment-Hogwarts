package Users;

public class Teacher extends User
{
    public Teacher(String firstName, String lastName, String username, String password, int houseId) throws Exception {
        super(firstName, lastName, username, password);
        this.houseID = houseId;
        this.roleID = 2; // Teacher's Role Id
    }
}
