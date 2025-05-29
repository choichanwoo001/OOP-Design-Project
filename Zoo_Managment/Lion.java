package Zoo_Management;

public class Lion extends Animal implements carnivore{

    public Lion(String name, String meal, String species) {
        super(name, meal, species);
    }

    public void dailyMeat(){
        super.meal = "Meat";
    }
}

