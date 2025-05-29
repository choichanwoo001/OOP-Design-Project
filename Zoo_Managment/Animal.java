package Zoo_Management;

public class Animal{
    String name;
    String meal;
    String species;

    public Animal(String name, String meal, String species) {
        this.name = name;
        this.meal = meal;
        this.species = species;
    }

    @Override
    public String toString() {
        return " name='" + name + '\'' +
                ", meal='" + meal + '\'' +
                ", species='" + species + '\''
                ;
    }
}

