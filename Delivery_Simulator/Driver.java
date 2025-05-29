package Delivery_simlulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Driver {
    private String name;
    private String vehicleType;
    private boolean isAvailable;
    private List<Order> deliveryHistory;
    private DriverStrategy driverStrategy;

    public Driver(String name, String vehicleType) {
        this.name = name;
        this.vehicleType = vehicleType;
        this.isAvailable = true;
        this.deliveryHistory = new ArrayList<>();

        // 차량 타입에 따라 적절한 전략 할당
        if (vehicleType.equalsIgnoreCase("car")) {
            this.driverStrategy = new CarDriver(name);
        } else if (vehicleType.equalsIgnoreCase("bike")) {
            this.driverStrategy = new BikeDriver(name);
        }
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // 멀티 쓰레드 구현을 위한 변경사항
    public void deliverOrder(Order order) {
        if (isAvailable) {
            isAvailable = false;
            deliveryHistory.add(order);
            driverStrategy.deliverOrder();

            // 별도의 스레드로 배달 작업 실행
            DeliveryTask deliveryTask = new DeliveryTask(order, this);
            Thread deliveryThread = new Thread(deliveryTask);
            deliveryThread.start();

            System.out.println(name + "님이 " + order.oderName + " 주문의 배달을 시작했습니다.");
            System.out.println("배달은 백그라운드에서 진행됩니다. 다른 작업을 계속 진행할 수 있습니다.");
        } else {
            System.out.println(name + "님은 현재 다른 배달 중입니다.");
        }
    }

    public int getEstimatedDeliveryTime() {
        return driverStrategy.deliveryTime;
    }

    public List<Order> getDeliveryHistory() {
        return deliveryHistory;
    }

    public String getVehicleType() {
        return vehicleType;
    }
}