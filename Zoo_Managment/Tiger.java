package Zoo_Management;

public class Tiger extends Animal implements carnivore{

    public Tiger(String name, String meal, String species) {
        super(name, meal, species);
    }

    public void dailyMeat(){
        super.meal = "Meat";
    }
}

