package backend;

public class CustomerListMenu implements Menu{
    private ApplicationContext context;
    private UserManagementService userManagementService;

    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        User[] customers = userManagementService.getUsers();
        if (customers.length == 0)
        {
            System.out.println("No Customers");
        }
        else {
            for (int i=0; i<customers.length; i++)
            {
                System.out.println(customers[i].toString());
            }
        }
        Menu menuToNavigate = new MainMenu();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** CUSTOMER LIST *****");
    }

}
