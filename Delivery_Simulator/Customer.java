package Delivery_simlulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer {
    String name;
    String address;
    String phoneNumber;
    List<Order> orderHistory;
    Scanner scanner = new Scanner(System.in);

    Customer(String name, String address, String phoneNumber){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.orderHistory = new ArrayList<>();
    }

    void createOrder(Restaurant restaurant, MenuItem menuItems){
        String restaurantName = restaurant.name;

        orderHistory.add(new Order(menuItems.name, this, restaurant, menuItems));
        System.out.println("주문 완료");
    }

    List<Order> viewOrderHistory(){
        return orderHistory;
    }

    void cancelOrder(Order order){
        if(orderHistory.contains(order)){
            orderHistory.remove(order);
            System.out.println("주문이 취소되었습니다.");
        }
        else {
            System.out.println("해당 주문이 요청된 적 없습니다.");
        }
    }
}
