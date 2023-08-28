package backend;

public class SignOutMenu implements Menu{
    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        printMenuHeader();
        System.out.println("Have a nice day! Look forward to welcoming back!");
        context.setLoggedInUser(null);

    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** SIGN OUT *****");
    }

}
