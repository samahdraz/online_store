package backend;

import java.util.Scanner;

public class SignUpMenu implements Menu{
    private UserManagementService userManagementService;
    private ApplicationContext context;

    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        printMenuHeader();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your first name:");
        String firstname = sc.next();
        System.out.print("Enter your last name:");
        String lastname = sc.next();
        System.out.print("Enter your email:");
        String email = sc.next();
        System.out.print("Enter your password:");
        String password = sc.next();

        User user = new DefaultUser(firstname, lastname, password, email);
        String message = userManagementService.registerUser(user);
        if (message.isEmpty())
        {
            context.setLoggedInUser(user);
            System.out.println("New user is created");
        }
        else {
            System.out.println(message);
        }

    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** SIGN UP *****");
    }

}
