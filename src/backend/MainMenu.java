package backend;

import java.util.Scanner;

public class MainMenu implements Menu{
    public static final String MENU_COMMAND = "menu";

    private static final String MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER = "Please, enter number in console to proceed." + System.lineSeparator()
            + "1. Sign Up" + System.lineSeparator() + "2. Sign In"
            + System.lineSeparator() + "3. Product Catalog" + System.lineSeparator()
            + "4. My Orders" + System.lineSeparator() + "5. Settings" + System.lineSeparator() +
            "6. Customer List";

    private static final String MAIN_MENU_TEXT_FOR_LOGGED_IN_USER = "Please, enter number in console to proceed." + System.lineSeparator()
            + "1. Sign Up" + System.lineSeparator() + "2. Sign Out"
            + System.lineSeparator() + "3. Product Catalog" + System.lineSeparator()
            + "4. My Orders" + System.lineSeparator() + "5. Settings" + System.lineSeparator() +
            "6. Customer List";;

    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        while (true){
            if (context.getMainMenu() == null)
                context.setMainMenu(this);
            Menu menuToNavigate = null;
            loop:while(true)
            {
                this.printMenuHeader();
                Scanner scanner = new Scanner(System.in);
                System.out.print("User input: ");
                String userInput = scanner.next();
                if (userInput.equalsIgnoreCase(MENU_COMMAND))
                    System.exit(0);
                else{
                    int number;
                    try {
                        number = Integer.parseInt(userInput);
                        switch (number){
                            case 1 :
                                menuToNavigate = new SignUpMenu();
                                break loop;
                            case 2 :
                                if (context.getLoggedInUser() == null)
                                    menuToNavigate = new SignInMenu();
                                else
                                    menuToNavigate = new SignOutMenu();
                                break loop;
                            case 3 :
                                menuToNavigate = new ProductCatalogMenu();
                                break loop;
                            case 4 :
                                menuToNavigate = new MyOrdersMenu();
                                break loop;
                            case 5 :
                                menuToNavigate = new SettingsMenu();
                                break loop;
                            case 6 :
                                menuToNavigate = new CustomerListMenu();
                                break loop;
                            default:
                                System.out.println("(\"Only 1, 2, 3, 4, 5 is allowed. Try one more time\"");
                                continue;
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("Please enter a number");
                    }
                }
            }
            menuToNavigate.start();
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** MAIN MENU *****");
        if (context.getLoggedInUser() == null)
            System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER);
        else
            System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_IN_USER);
    }

}
