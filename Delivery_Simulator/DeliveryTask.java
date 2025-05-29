package Delivery_simlulator;

public class DeliveryTask implements Runnable {
    private Order order;
    private Driver driver;

    public DeliveryTask(Order order, Driver driver) {
        this.order = order;
        this.driver = driver;
    }

    @Override
    public void run() {
        try {
            // 배달 시작 - 주문 상태 업데이트
            System.out.println("[배달 스레드] " + driver.getName() + "님이 " + order.oderName + " 주문 배달을 시작합니다.");
            order.updateState(new DeliveringOrder());

            // 배달 시간 시뮬레이션
            int deliveryTime = driver.getEstimatedDeliveryTime() * 1000; // 초 단위로 변환
            System.out.println("[배달 스레드] " + order.oderName + " 배달 중... 예상 시간: " + (deliveryTime/1000) + "분");

            // 배달 진행 상황 업데이트를 위한 간격 (1초마다)
            int updateInterval = 1000;
            int elapsedTime = 0;

            // 실제 배달 시간 시뮬레이션 - 중간에 상태 업데이트
            while (elapsedTime < deliveryTime) {
                Thread.sleep(updateInterval);
                elapsedTime += updateInterval;

                // 배달 관리자에게 상태 업데이트 알림
                DeliveryManager.getInstance().updateDeliveryStatus(order, false);

                // 진행률 출력
                int progress = (elapsedTime * 100) / deliveryTime;
                if (progress % 25 == 0) { // 25%, 50%, 75% 진행 시 메시지 출력
                    System.out.println("[배달 스레드] " + order.oderName + " 배달 진행률: " + progress + "%");
                }
            }

            // 배달 완료 - 주문 상태 업데이트
            System.out.println("[배달 스레드] " + driver.getName() + "님이 " + order.oderName + " 주문 배달을 완료했습니다.");
            order.updateState(new CompletedOrder());

            // 기사 상태 업데이트
            driver.setAvailable(true);

            // 배달 관리자에게 완료 알림
            DeliveryManager.getInstance().updateDeliveryStatus(order, true);

        } catch (InterruptedException e) {
            System.out.println("[배달 스레드] 배달이 중단되었습니다: " + e.getMessage());
            driver.setAvailable(true); // 중단된 경우에도 기사를 사용 가능 상태로 변경
        }
    }
}