package Delivery_simlulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Restaurant restaurant = null;
        DriverManager driverManager = DriverManager.getInstance();
        DeliveryManager deliveryManager = DeliveryManager.getInstance();

        // 고객 이름
        System.out.print("고객님 이름을 입력해주세요 :");
        String name = s.nextLine();
        System.out.print("고객님 주소를 입력해주세요 :");
        String address = s.nextLine();
        System.out.print("고객님 핸드폰 번호를 입력해주세요 :");
        String phoneNumber = s.nextLine();

        Customer cust1 = new Customer(name, address, phoneNumber);
        List<Order> activeOrders = new ArrayList<>();

        while(true){
            System.out.println("\n===== 배달 시뮬레이터 =====");
            System.out.println("1. 주문하기");
            System.out.println("2. 주문 취소");
            System.out.println("3. 주문 조회");
            System.out.println("4. 결제 방법 선택");
            System.out.println("5. 주문 상세 정보");
            System.out.println("6. 배달 시작");
            System.out.println("7. 주문 상태 확인");
            System.out.println("8. 진행 중인 배달 조회");
            System.out.println("0. 종료");
            System.out.print("메뉴를 선택하세요: ");

            int option = s.nextInt();
            s.nextLine(); // 개행문자 처리

            if(option == 1) {
                System.out.println("\n===== 식당 선택 =====");
                System.out.println("1. 정돈 (돈까스 전문점)");
                System.out.println("2. 시골야채된장 (한식)");
                System.out.println("3. 딘타이펑 (중식)");
                System.out.println("4. 고에몬 (양식)");
                System.out.print("식당을 선택하세요: ");
                int resOption = s.nextInt();
                s.nextLine(); // 개행문자 처리

                if(resOption == 1) {
                    restaurant = new Restaurant1("정돈", "강남역 1번 출구");
                }
                else if(resOption == 2) {
                    restaurant = new Restaurant2("시골야채된장", "강남역 2번 출구");
                }
                else if(resOption == 3) {
                    restaurant = new Restaurant3("딘타이펑", "강남역 3번 출구");
                }
                else if(resOption == 4) {
                    restaurant = new Restaurant4("고에몬", "강남역 4번 출구");
                }
                else {
                    System.out.println("해당 식당은 존재하지 않습니다.");
                    continue;
                }

                System.out.println("\n===== 메뉴 선택 =====");
                Objects.requireNonNull(restaurant).showMenu();
                System.out.print("메뉴 번호를 선택하세요 (1-4): ");
                int menuOption = s.nextInt();
                s.nextLine(); // 개행문자 처리

                if (menuOption < 1 || menuOption > 4) {
                    System.out.println("잘못된 메뉴 번호입니다.");
                    continue;
                }

                cust1.createOrder(restaurant, restaurant.menu.get(menuOption-1));
                activeOrders.add(cust1.viewOrderHistory().get(cust1.viewOrderHistory().size() - 1));
                System.out.println(restaurant.menu.get(menuOption-1).name + " 주문이 생성되었습니다.");
            }
            else if (option == 2) {
                if (cust1.viewOrderHistory().isEmpty()) {
                    System.out.println("취소할 주문이 없습니다.");
                    continue;
                }

                System.out.println("\n===== 주문 취소 =====");
                List<Order> orders = cust1.viewOrderHistory();
                for (int i = 0; i < orders.size(); i++) {
                    System.out.println((i+1) + ". " + orders.get(i).oderName + " - " + orders.get(i).getOrderStateName());
                }

                System.out.print("취소할 주문 번호를 선택하세요: ");
                int orderOption = s.nextInt();
                s.nextLine(); // 개행문자 처리

                if (orderOption < 1 || orderOption > orders.size()) {
                    System.out.println("잘못된 주문 번호입니다.");
                    continue;
                }

                Order orderToCancel = orders.get(orderOption-1);
                // 배달 중이거나 완료된 주문 취소 불가
                if (orderToCancel.getCurrentState() instanceof DeliveringOrder ||
                        orderToCancel.getCurrentState() instanceof CompletedOrder) {
                    System.out.println("배달 중이거나 완료된 주문은 취소할 수 없습니다.");
                    continue;
                }

                cust1.cancelOrder(orderToCancel);
                activeOrders.remove(orderToCancel);
            }
            else if (option == 3) {
                System.out.println("\n===== 주문 조회 =====");
                List<Order> orders = cust1.viewOrderHistory();
                if (orders.isEmpty()) {
                    System.out.println("주문 내역이 없습니다.");
                    continue;
                }

                for (int i = 0; i < orders.size(); i++) {
                    System.out.println((i+1) + ". " + orders.get(i).oderName +
                            " - 상태: " + orders.get(i).getOrderStateName() +
                            " - 결제: " + (orders.get(i).isPaid() ? "완료" : "미결제"));
                }
            }
            else if (option == 4) {
                if (activeOrders.isEmpty()) {
                    System.out.println("결제할 주문이 없습니다. 먼저 주문을 생성해주세요.");
                    continue;
                }

                System.out.println("\n===== 결제 방법 선택 =====");
                List<Order> orders = cust1.viewOrderHistory();
                for (int i = 0; i < orders.size(); i++) {
                    System.out.println((i+1) + ". " + orders.get(i).oderName +
                            " - 금액: " + orders.get(i).getTotalAmount() + "원" +
                            " - 결제: " + (orders.get(i).isPaid() ? "완료" : "미결제"));
                }

                System.out.print("결제할 주문 번호를 선택하세요: ");
                int orderOption = s.nextInt();
                s.nextLine(); // 개행문자 처리

                if (orderOption < 1 || orderOption > orders.size()) {
                    System.out.println("잘못된 주문 번호입니다.");
                    continue;
                }

                Order orderToPay = orders.get(orderOption-1);
                if (orderToPay.isPaid()) {
                    System.out.println("이미 결제가 완료된 주문입니다.");
                    continue;
                }

                System.out.println("\n===== 결제 방법 =====");
                System.out.println("1. 신용카드");
                System.out.println("2. 카카오페이");
                System.out.println("3. 계좌이체");
                System.out.print("결제 방법을 선택하세요: ");
                int paymentOption = s.nextInt();
                s.nextLine(); // 개행문자 처리

                switch (paymentOption) {
                    case 1:
                        orderToPay.setPaymentMethod(new CreditCardPayment());
                        break;
                    case 2:
                        orderToPay.setPaymentMethod(new KakaoPayPayment());
                        break;
                    case 3:
                        orderToPay.setPaymentMethod(new BankTransferPayment());
                        break;
                    default:
                        System.out.println("잘못된 결제 방법입니다.");
                        continue;
                }

                orderToPay.processPayment();
            }
            else if (option == 5) {
                if (cust1.viewOrderHistory().isEmpty()) {
                    System.out.println("조회할 주문이 없습니다.");
                    continue;
                }

                System.out.println("\n===== 주문 상세 정보 =====");
                List<Order> orders = cust1.viewOrderHistory();
                for (int i = 0; i < orders.size(); i++) {
                    System.out.println((i+1) + ". " + orders.get(i).oderName);
                }

                System.out.print("상세 정보를 볼 주문 번호를 선택하세요: ");
                int orderOption = s.nextInt();
                s.nextLine(); // 개행문자 처리

                if (orderOption < 1 || orderOption > orders.size()) {
                    System.out.println("잘못된 주문 번호입니다.");
                    continue;
                }

                orders.get(orderOption-1).displayOrderDetails();
            }
            else if (option == 6) {
                if (activeOrders.isEmpty()) {
                    System.out.println("배달 가능한 주문이 없습니다.");
                    continue;
                }

                System.out.println("\n===== 배달 시작 =====");
                List<Order> orders = new ArrayList<>();

                for (Order order : cust1.viewOrderHistory()) {
                    if (order.isPaid() && !(order.getCurrentState() instanceof DeliveringOrder) &&
                            !(order.getCurrentState() instanceof CompletedOrder)) {
                        orders.add(order);
                    }
                }

                if (orders.isEmpty()) {
                    System.out.println("배달 가능한 주문이 없습니다. 먼저 결제를 완료해주세요.");
                    continue;
                }

                for (int i = 0; i < orders.size(); i++) {
                    System.out.println((i+1) + ". " + orders.get(i).oderName +
                            " - 음식점: " + orders.get(i).restaurant.name);
                }

                System.out.print("배달을 시작할 주문 번호를 선택하세요: ");
                int orderOption = s.nextInt();
                s.nextLine(); // 개행문자 처리

                if (orderOption < 1 || orderOption > orders.size()) {
                    System.out.println("잘못된 주문 번호입니다.");
                    continue;
                }

                Order orderToDeliver = orders.get(orderOption-1);

                // 배달 기사 할당이 안된 경우
                if (orderToDeliver.getAssignedDriver() == null) {
                    Driver driver = driverManager.assignDriver(orderToDeliver);
                    if (driver == null) {
                        System.out.println("배달 기사 할당에 실패했습니다. 다시 시도해주세요.");
                        continue;
                    }
                }

                orderToDeliver.startDelivery();
            }
            else if (option == 7) {
                if (cust1.viewOrderHistory().isEmpty()) {
                    System.out.println("주문 내역이 없습니다.");
                    continue;
                }

                System.out.println("\n===== 주문 상태 확인 =====");
                List<Order> orders = cust1.viewOrderHistory();
                for (Order order : orders) {
                    System.out.println("주문: " + order.oderName +
                            "\n상태: " + order.getOrderStateName() +
                            "\n결제: " + (order.isPaid() ? "완료" : "미결제") +
                            "\n배달 기사: " + (order.getAssignedDriver() != null ? order.getAssignedDriver().getName() : "미배정"));

                    // 고객에게 알림
                    order.notifyCustomer();
                    System.out.println("------------------------------");
                }
            }
            else if (option == 8) {
                System.out.println("\n===== 진행 중인 배달 조회 =====");
                List<DeliveryManager.DeliveryStatus> activeDeliveries = deliveryManager.getActiveDeliveries();

                if (activeDeliveries.isEmpty()) {
                    System.out.println("현재 진행 중인 배달이 없습니다.");
                } else {
                    System.out.println("총 " + activeDeliveries.size() + "건의 배달이 진행 중입니다.");
                    for (DeliveryManager.DeliveryStatus status : activeDeliveries) {
                        System.out.println("------------------------------");
                        System.out.println("주문: " + status.getOrder().oderName);
                        System.out.println("배달 기사: " + status.getDriver().getName() +
                                " (" + status.getDriver().getVehicleType() + ")");
                        System.out.println("상태: " + status.getStatusDescription());
                    }
                }
            }
            else if (option == 0) {
                break;
            }
            else {
                System.out.println("잘못된 메뉴를 선택하셨습니다.");
            }
        }

        System.out.println("\n===== 주문 내역 =====");
        List<Order> orders = cust1.viewOrderHistory();
        if (orders.isEmpty()) {
            System.out.println("주문 내역이 없습니다.");
        } else {
            for (Order order : orders) {
                System.out.println("주문: " + order.oderName +
                        " - 상태: " + order.getOrderStateName() +
                        " - 결제: " + (order.isPaid() ? "완료" : "미결제"));
            }
        }

        System.out.println("\n배달 시뮬레이터를 종료합니다. 이용해주셔서 감사합니다!");

        // 스레드 풀 종료
        deliveryManager.shutdown();
    }
}