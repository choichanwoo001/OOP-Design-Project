package Delivery_simlulator;

import java.util.ArrayList;
import java.util.List;


public class Order {
    String oderName;
    Customer customer;
    Restaurant restaurant;
    List<MenuItem> items = new ArrayList<>();
    double totalAmount;
    OrderState currentState;
    Driver assignedDriver;
    PaymentStrategy paymentMethod;
    boolean isPaid = false;

    Order(String oderName, Customer customer, Restaurant restaurant, MenuItem items){
        this.oderName = oderName;
        this.customer = customer;
        this.restaurant = restaurant;
        this.items.add(items);
        this.currentState = new PendingOrder();
        this.totalAmount = calculateTotalAmount();}
        public OrderState getCurrentState() {
            return currentState;
        }

        public String getOrderStateName() {
            return currentState.getClass().getSimpleName();
        }

        public boolean isPaid() {
            return isPaid;
        }

        public Driver getAssignedDriver() {
            return assignedDriver;
        }

        public double getTotalAmount() {
            return totalAmount;
        }


    void updateState(OrderState state){
        this.currentState = state;
        this.currentState.handle(this);
    }

    double calculateTotalAmount(){
        totalAmount = 0;
        for(MenuItem item : items){
            totalAmount += item.price;
        }
        return totalAmount;
    }

    void assignDriver(Driver driver){
        this.assignedDriver = driver;
        System.out.println(driver.getName() + " 기사님이 " + this.oderName + " 주문의 배달을 맡았습니다.");
    }

    boolean processPayment(){
        if (paymentMethod == null) {
            System.out.println("결제 방법이 선택되지 않았습니다.");
            return false;
        }

        if (isPaid) {
            System.out.println("이미 결제가 완료된 주문입니다.");
            return true;
        }

        System.out.println(totalAmount + "원을 결제합니다.");
        paymentMethod.pay((int)totalAmount);
        isPaid = true;

        // 결제 후 조리 상태로 변경
        updateState(new CookingOrder());
        return true;
    }

    void notifyCustomer(){
        System.out.println(customer.name + "님의 " + oderName + " 주문이 " +
                (currentState instanceof CompletedOrder ? "배달 완료되었습니다." :
                        currentState instanceof DeliveringOrder ? "배달 중입니다." :
                                currentState instanceof CookingOrder ? "조리 중입니다." : "접수되었습니다."));
    }

    public void setState(OrderState orderState) {
        this.currentState = orderState;
    }

    public void setPaymentMethod(PaymentStrategy paymentMethod) {
        this.paymentMethod = paymentMethod;
        System.out.println(paymentMethod.getClass().getSimpleName() + "로 결제 방법이 설정되었습니다.");
    }

    public void startDelivery() {
        if (!isPaid) {
            System.out.println("결제가 완료되지 않아 배달을 시작할 수 없습니다.");
            return;
        }

        if (assignedDriver == null) {
            System.out.println("배달 기사가 배정되지 않았습니다.");
            return;
        }

        // DeliveryManager를 통해 멀티스레드 배달 시작
        DeliveryManager.getInstance().startDelivery(this, assignedDriver);
    }

    public void displayOrderDetails() {
        System.out.println("======= 주문 상세 정보 =======");
        System.out.println("주문 이름: " + oderName);
        System.out.println("고객 이름: " + customer.name);
        System.out.println("배달 주소: " + customer.address);
        System.out.println("음식점 이름: " + restaurant.name);
        System.out.println("주문 금액: " + totalAmount + "원");
        System.out.println("결제 상태: " + (isPaid ? "결제 완료" : "미결제"));
        System.out.println("주문 상태: " + currentState.getClass().getSimpleName());
        System.out.println("배달 기사: " + (assignedDriver != null ? assignedDriver.getName() : "미배정"));
        System.out.println("=============================");
    }
}