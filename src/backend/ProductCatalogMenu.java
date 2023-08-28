package backend;

import java.util.Scanner;

public class ProductCatalogMenu implements Menu{
    private static final String CHECKOUT_COMMAND = "checkout";
    private ApplicationContext context;
    private ProductManagementService productManagementService;

    {
        context = ApplicationContext.getInstance();
        productManagementService = DefaultProductManagementService.getInstance();
    }

    @Override
    public void start() {
        Menu menuToNavigate = null;
        while (true) {
            printMenuHeader();
            printProducts();

            System.out.print("Product ID to add to cart or enter 'checkout' to proceed with checkout: ");
            Scanner sc = new Scanner(System.in);
            String userInput = sc.next();

            if (context.getLoggedInUser() == null) {
                System.out.println("You are not logged in. Please, sign in or create new account");
                menuToNavigate = new MainMenu();
                break;
            }

            if (userInput.equalsIgnoreCase(MainMenu.MENU_COMMAND)) {
                menuToNavigate = new MainMenu();
                break;
            }
            else if (userInput.equalsIgnoreCase(CHECKOUT_COMMAND)) {
                Cart cart = context.getSessionCart();
                if (cart.isEmpty() || cart == null) {
                    System.out.println("Your cart is empty. Please, add product to cart first and then proceed with checkout");
                } else {
                    menuToNavigate = new CheckoutMenu();
                    break;
                }
            }
            else {
                try{
                    int id = Integer.parseInt(userInput);
                    try{
                        Product product = productManagementService.getProductById(id);
                        context.getSessionCart().addProduct(product);
                        System.out.printf("Product %s has been added to your cart. "
                                + "If you want to add a new product - enter the product id. "
                                + "If you want to proceed with checkout - enter word "
                                + "'checkout' to console %n", product.getProductName());
                    }
                    catch (NullPointerException e)
                    {
                        System.out.println("Please, enter product ID if you want to add product to cart. Or enter ‘checkout’ if you want to proceed with checkout. Or enter ‘menu’ if you want to navigate back to the main menu.");
                    }
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Enter something valid please");
                }
            }
        }
        menuToNavigate.start();
    }



    private void printProducts(){
        Product[] products = productManagementService.getProducts();
        for (int i=0; i<products.length; i++){
            System.out.println(products[i]);
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** PRODUCT CATALOG *****");
        System.out.println("Enter product id to add it to the cart or 'menu' if you want to navigate back to the main menu");
    }

}
