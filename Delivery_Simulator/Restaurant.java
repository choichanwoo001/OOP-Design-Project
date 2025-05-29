package Delivery_simlulator;

import java.util.*;

abstract public class Restaurant {
    String name;
    String address;
    List<MenuItem> menu;
    List<Order> pendingOrders;

    Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
        this.menu = new ArrayList<>();
        this.pendingOrders = new ArrayList<>();
    }

    void acceptOrder(Order order) {
    }

    void rejectOrder(Order order) {
    }

    void prepareOrder(Order order) {

    }

    void completeOrder(Order order) {

    }

    void updateOrder(Order order) {

    }

    abstract void restaurantInit();

    abstract void showMenu();
}

