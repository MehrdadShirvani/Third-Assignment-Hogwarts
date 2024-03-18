package Users;
import Utilities.HashText;
import java.util.Objects;
import java.util.UUID;

public class User implements UserManagement {


    //region Fields
    protected UUID userID;
    protected String username;
    protected String firstName;
    protected String lastName;
    protected int roleID;
    protected int houseID;
    // TODO: Passwords should hashed
    private String password;
    //endregion

    public User(String firstName, String lastName, String username, String password) throws Exception
    {
        this.username = username;
        if(UserManagement.isUsernameUnique(username) == false)
        {
            throw new Exception("Username was not unique");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.password = HashText.encodeText(password);
    }
    @Override
    public boolean validatePassword(String enteredPassword)
    {
        return HashText.encodeText(enteredPassword).equals(password);
    }

    @Override
    public void changeUsername(String newUsername)
    {
        if(username.equals(newUsername) == false || UserManagement.isUsernameUnique(newUsername))
        {
            username = newUsername;
            //TODO Change the list as well
            return;
        }

    }

    @Override
    public void changePassword(String newPassword)
    {
        password = HashText.encodeText(newPassword);
    }
}