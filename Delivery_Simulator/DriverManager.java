package Delivery_simlulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DriverManager {
    private List<Driver> availableDrivers;
    private static DriverManager instance;

    private DriverManager() {
        availableDrivers = new ArrayList<>();
        initializeDrivers();
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    private void initializeDrivers() {
        // 초기 드라이버 목록 생성
        availableDrivers.add(new Driver("김기사", "car"));
        availableDrivers.add(new Driver("이기사", "bike"));
        availableDrivers.add(new Driver("박기사", "car"));
        availableDrivers.add(new Driver("정기사", "bike"));
        availableDrivers.add(new Driver("최기사", "car"));
    }

    public Driver assignDriver(Order order) {
        List<Driver> availableDriversList = new ArrayList<>();

        // 가용 드라이버 찾기
        for (Driver driver : availableDrivers) {
            if (driver.isAvailable()) {
                availableDriversList.add(driver);
            }
        }

        if (availableDriversList.isEmpty()) {
            System.out.println("현재 가용 드라이버가 없습니다. 잠시 후 다시 시도해주세요.");
            return null;
        }

        // 랜덤으로 드라이버 선택
        Random random = new Random();
        Driver selectedDriver = availableDriversList.get(random.nextInt(availableDriversList.size()));

        // 배달 할당
        order.assignDriver(selectedDriver);
        System.out.println(selectedDriver.getName() + " 기사님이 배정되었습니다. 배달 수단: " + selectedDriver.getVehicleType());

        return selectedDriver;
    }

    public List<Driver> getAvailableDrivers() {
        return availableDrivers;
    }
}