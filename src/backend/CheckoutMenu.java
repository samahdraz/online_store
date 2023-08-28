package backend;

import java.util.Scanner;

public class CheckoutMenu implements Menu{
    private ApplicationContext context;
    private OrderManagementService orderManagementService;

    {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
    }

    @Override
    public void start() {
        while (true)
        {
            printMenuHeader();
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your credit card number without spaces and press enter if you confirm purchase");
            String creditNumber = sc.next();

            Order order = new DefaultOrder();
            if (order.isCreditCardNumberValid(creditNumber))
            {
                order.setCreditCardNumber(creditNumber);
                order.setCustomerId(context.getLoggedInUser().getId());
                order.setProducts(context.getSessionCart().getProducts());
                orderManagementService.addOrder(order);
                context.getSessionCart().clear();
                break;
            }
            else {
                System.out.println("You entered invalid credit card number. Valid credit card should contain 16 digits. Please, try one more time");
                continue;
            }
        }
        System.out.println("Thanks a lot for your purchase. Details about order delivery are sent to your email");

    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** CHECK OUT *****");
    }
}
