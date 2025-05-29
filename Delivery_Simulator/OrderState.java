package Delivery_simlulator;

public interface OrderState {
    void nextState(Order order);
    void handle(Order order);
}

class PendingOrder implements OrderState{
    @Override
    public void nextState(Order order) {
        order.setState(new CookingOrder()); // 다음 차례
    }

    @Override
    public void handle(Order order) {
        System.out.println("Order is pending");
    }
}

class CookingOrder implements OrderState{
    public void nextState(Order order) {
        order.setState(new DeliveringOrder());
    }

    public void handle(Order order) {
        System.out.println("Order is cooking");
    }
}

class DeliveringOrder implements OrderState{
    public void nextState(Order order) {
        order.setState(new CompletedOrder());
    }

    @Override
    public void handle(Order order) {
        System.out.println( "Order is delivering");
    }
}

class CompletedOrder implements OrderState{
    public void nextState(Order order) {
        System.out.println("Order is already completed");
    }

    public void handle(Order order) {
        System.out.println("Order is completed");
    }
}


