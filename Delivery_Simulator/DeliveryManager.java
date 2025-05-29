package Delivery_simlulator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeliveryManager {
    private static DeliveryManager instance;
    private ExecutorService deliveryExecutor;
    private List<DeliveryStatus> activeDeliveries;

    private DeliveryManager() {
        // 스레드 풀 생성 - 최대 5개의 동시 배달 처리
        deliveryExecutor = Executors.newFixedThreadPool(5);
        activeDeliveries = new ArrayList<>();
    }

    public static DeliveryManager getInstance() {
        if (instance == null) {
            instance = new DeliveryManager();
        }
        return instance;
    }

    public void startDelivery(Order order, Driver driver) {
        if (driver == null || !driver.isAvailable()) {
            System.out.println("배달 기사가 배정되지 않았거나 사용 불가능합니다.");
            return;
        }

        if (!order.isPaid()) {
            System.out.println("결제가 완료되지 않은 주문은 배달할 수 없습니다.");
            return;
        }

        // 배달 상태 객체 생성
        DeliveryStatus status = new DeliveryStatus(order, driver);
        activeDeliveries.add(status);

        // 배달 작업 생성 및 실행
        DeliveryTask task = new DeliveryTask(order, driver);
        deliveryExecutor.submit(task);

        System.out.println(driver.getName() + " 기사님이 " + order.oderName + " 주문 배달을 시작했습니다.");
        System.out.println("현재 진행 중인 배달: " + activeDeliveries.size() + "건");
    }

    public List<DeliveryStatus> getActiveDeliveries() {
        return new ArrayList<>(activeDeliveries);
    }

    public void updateDeliveryStatus(Order order, boolean completed) {
        for (int i = 0; i < activeDeliveries.size(); i++) {
            DeliveryStatus status = activeDeliveries.get(i);
            if (status.getOrder() == order) {
                if (completed) {
                    activeDeliveries.remove(i);
                    System.out.println("[배달 관리자] " + order.oderName + " 배달이 완료되어 목록에서 제거되었습니다.");
                } else {
                    status.updateProgress();
                }
                break;
            }
        }
    }

    public void shutdown() {
        deliveryExecutor.shutdown();
    }

    // 배달 상태를 저장하는 내부 클래스
    public static class DeliveryStatus {
        private Order order;
        private Driver driver;
        private long startTime;
        private int progress;

        public DeliveryStatus(Order order, Driver driver) {
            this.order = order;
            this.driver = driver;
            this.startTime = System.currentTimeMillis();
            this.progress = 0;
        }

        public Order getOrder() {
            return order;
        }

        public Driver getDriver() {
            return driver;
        }

        public int getProgress() {
            return progress;
        }

        public void updateProgress() {
            // 실제 경과 시간 기반으로 진행률 계산 (최대 100%)
            long elapsedTime = System.currentTimeMillis() - startTime;
            long estimatedTime = driver.getEstimatedDeliveryTime() * 1000;
            progress = (int) Math.min(100, (elapsedTime * 100) / estimatedTime);
        }

        public long getElapsedTime() {
            return (System.currentTimeMillis() - startTime) / 1000; // 초 단위
        }

        public String getStatusDescription() {
            updateProgress();
            return String.format("%s - 진행률: %d%% (경과 시간: %d초)",
                    order.oderName, progress, getElapsedTime());
        }
    }
}