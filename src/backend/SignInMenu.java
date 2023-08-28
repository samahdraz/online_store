package backend;

import java.util.Scanner;

public class SignInMenu implements Menu{
    private ApplicationContext context;
    private UserManagementService userManagementService;

    {
        context = ApplicationContext.getInstance();
        userManagementService = DefaultUserManagementService.getInstance();
    }

    @Override
    public void start() {
        printMenuHeader();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your email:");
        String email = sc.next();
        System.out.print("Enter your password:");
        String password = sc.next();
        try{
            User currentUser = userManagementService.getUserByEmail(email);
            if (currentUser.getPassword().equals(password))
            {
                context.setLoggedInUser(currentUser);
                System.out.println("Glad to see you back "+currentUser.getFirstName()+" "+currentUser.getLastName());
            }
            else {
                System.out.println("Unfortunately, such login and password doesn’t exist");
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("Unfortunately, such login and password doesn’t exist(NULL)");
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** SIGN IN *****");
    }

}
