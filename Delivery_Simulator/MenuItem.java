package Delivery_simlulator;

public class MenuItem {
    String name;
    int price;
    String description;
    boolean isAvailable;

    MenuItem(String name, int price){
        this.name = name;
        this.price = price;
        this.isAvailable = true;
    }

    void updatePrice(int price){
        this.price = price;
    }

    void setAvailability(boolean availability){
        this.isAvailable = availability;
    }
}
