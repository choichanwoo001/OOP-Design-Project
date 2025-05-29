package Zoo_Management;

public class Penguin extends Animal implements Herbivore {
    public Penguin(String name, String meal, String species) {
        super(name, meal, species);
    }

    public void dailyPlants(){
        super.meal = "Plants";
    }
}

