package backend;

import java.util.ArrayList;
import java.util.List;

public class DefaultOrderManagementService implements OrderManagementService{
    //private static final int DEFAULT_ORDER_CAPACITY = 10;

    private static DefaultOrderManagementService instance;

    private List<Order> orders ;

    {
        orders = new ArrayList<Order>();
    }

    public static OrderManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultOrderManagementService();
        }
        return instance;
    }

    @Override
    public void addOrder(Order order) {
       orders.add(order);
    }

    @Override
    public Order[] getOrdersByUserId(int userId) {
        List<Order> myOrder = new ArrayList<Order>();
        for (int i =0; i<orders.size(); i++)
        {
            if (orders.get(i).getCustomerId() == userId)
            {
                myOrder.add(orders.get(i));
            }
        }
        return myOrder.toArray(new Order[0]);
    }

    @Override
    public Order[] getOrders() {
        return orders.toArray(new Order[0]);
    }

    void clearServiceState() {
        orders = new ArrayList<Order>();
    }

}
