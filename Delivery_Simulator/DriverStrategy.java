package Delivery_simlulator;

public abstract class DriverStrategy {
    String name;
    int deliveryTime;
    int fuelCost;
    String OptimizeWay;
    public abstract void deliverOrder();
}

class CarDriver extends DriverStrategy{
    CarDriver(String name){
        this();
        super.name = name;
    }

    CarDriver(){
        super.deliveryTime = 10;
        super.fuelCost = 10;
        super.OptimizeWay = "Not fully optimized";
    }

    @Override
    public void deliverOrder() {
        System.out.println("CarDriver is delivering order");
    }
}

class BikeDriver extends DriverStrategy{
    BikeDriver(String name){
        this();
        super.name = name;
    }

    BikeDriver(){
        super.deliveryTime = 5;
        super.fuelCost = 5;
        super.OptimizeWay = "fully optimized";
    }

    @Override
    public void deliverOrder() {
        System.out.println("BikeDriver is delivering order");
    }
}