package Zoo_Management;

public class Elephant extends Animal implements Herbivore {
    public Elephant(String name, String meal, String species) {
        super(name, meal, species);
    }

    public void dailyPlants(){
        super.meal = "Plants";
    }
}
