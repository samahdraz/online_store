package backend;

import java.util.ArrayList;
import java.util.List;

public class DefaultUserManagementService implements UserManagementService{
    private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
    private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
    //private static final String CREATED_MESSAGE = "New user is created";

    //private static final int DEFAULT_USERS_CAPACITY = 10;

    private static DefaultUserManagementService instance;

    private List<User> users;

    {
        users = new ArrayList<User>();
    }
    private DefaultUserManagementService() {
    }

    @Override
    public String registerUser(User user) {
        if (user.getEmail().isEmpty())
            return EMPTY_EMAIL_ERROR_MESSAGE;
        else
        {
            if(checkUniqueEmail(user.getEmail()))
            {
                users.add(user);
                return "";
            }
            else
            {
                return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
            }
        }
    }

    public static UserManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultUserManagementService();
        }
        return instance;
    }


    @Override
    public User[] getUsers() {
        return users.toArray(new User[0]);
    }

    @Override
    public User getUserByEmail(String userEmail) {
        for (int i=0; i< users.size(); i++)
        {
            if (users.get(i).getEmail().equalsIgnoreCase(userEmail))
                return users.get(i);
        }
        return null;
    }

    private boolean checkUniqueEmail(String email)
    {
        for (int i=0; i< users.size(); i++)
        {
            if (users.get(i).getEmail().equalsIgnoreCase(email))
                return false;
        }
        return true;
    }

    void clearServiceState() {
        users = new ArrayList<User>();
    }
}
