package Users;

import Hogwarts.Hogwarts;

import java.util.List;
import java.util.Objects;

public class Assistant extends User{
    public Assistant(String firstName, String lastName, String username, String password) throws Exception {
        super(firstName, lastName, username, password);
        this.roleID = 1;
    }

    public void addNewAssistant(Assistant assistant)
    {
        Hogwarts.addUser(assistant);
    }

    public void removeUser(User user)
    {
       Hogwarts.removeUser(user);
    }

}
