package backend;

import java.util.Scanner;

public class ChangePasswordMenu implements Menu{
    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        System.out.print("Enter new password:");
        Scanner sc = new Scanner(System.in);
        String string = sc.next();

        User user = context.getLoggedInUser();
        user.setPassword(string);

        System.out.println("Your password has been successfully changed");
        Menu menuToNavigate = new MainMenu();
        menuToNavigate.start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** CHANGE PASSWORD *****");
    }

}
