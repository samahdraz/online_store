package backend;

public interface Order {

    boolean isCreditCardNumberValid(String userInput);

    void setCreditCardNumber(String userInput);

    void setProducts(Product[] products);

    void setCustomerId(int customerId);

    int getCustomerId();

}
