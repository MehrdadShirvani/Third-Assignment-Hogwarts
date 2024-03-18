package Users;

import Users.User;

public interface UserManagement {
    boolean validatePassword(String enteredPassword);
    public void changeUsername(String newUsername);
    public void changePassword(String newPassword);
    public static User login(String username, String password)
    {
        //TODO: implement functionality
        return null;
    }

    public static boolean isUsernameUnique(String username)
    {
        //TODO: implement functionality
        return true;
    }

}
