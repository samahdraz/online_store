package backend;

import java.util.ArrayList;
import java.util.List;

public class DefaultCart implements Cart{

    List<Product> products;

    {
        products = new ArrayList<Product>();
    }

    @Override
    public boolean isEmpty() {
       if(products.isEmpty())
           return true;
       return false;
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public Product[] getProducts() {
        return products.toArray(new Product[0]);
    }

    @Override
    public void clear() {
        products = new ArrayList<Product>();
    }

}
