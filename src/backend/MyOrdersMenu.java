package backend;

public class MyOrdersMenu implements Menu{
    private ApplicationContext context;
    private OrderManagementService orderManagementService;

    {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
    }

    @Override
    public void start() {
        Menu menuToNavigate = null;
        while (true){
            printMenuHeader();
            if (context.getLoggedInUser() == null)
            {
                System.out.println("Please, log in or create new account to see list of your orders");
                break;
            }
            else {
               Order[] orders = orderManagementService.getOrdersByUserId(context.getLoggedInUser().getId());
               if (orders.length == 0)
               {
                   System.out.println("Unfortunately, you don’t have any orders yet.");
                   break;
               }
               else {
                   for(int i =0; i<orders.length; i++)
                   {
                       System.out.println(orders[i].toString());
                   }
                   break;
               }
            }
        }
        menuToNavigate = new MainMenu();
        menuToNavigate.start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** MY ORDERS *****");
    }

}
