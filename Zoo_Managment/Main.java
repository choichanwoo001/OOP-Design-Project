package Zoo_Management;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Zoo_Function function = new Zoo_Function();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Maneger> manegers = new ArrayList<>();
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("1 - 동물등록 | 2 - 사육사 등록 | 3 - 동물 정보 보기");

            System.out.print("옵션을 입력해주세요 : ");
            int option = s.nextInt(); s.nextLine();
            if (option == 1) { // 동물 등록
                System.out.print("동물 이름을 입력해 주세요 : ");String name = s.nextLine().trim();
                System.out.print("어떤 동물인가요? : "); String type = s.nextLine().trim();
                animals.add(createAnimal(animals, name, type));
            }
            else if (option == 2) { // 사육사 등록
                System.out.print("사육사 이름을 입력해 주세요 : "); String name = s.nextLine();
                function.addManeger(manegers, new Maneger(name));
            }
            else if (option == 3) { // 동물 정보 보기
                function.ManegerMatchAnimal(animals, manegers);
                function.viewManegerAnimals(manegers);
            }
            else // 종료
                break;
        }

        function.addAnimal(animals, new Elephant("ele1", "Plants", "Herbivore"));
        manegers.add(new Maneger("박찬우"));
    }

    public static Animal createAnimal(ArrayList<Animal> animals,String name, String type){
        Animal newAnimal = null;
        if(type.equalsIgnoreCase("Elephant"))
            newAnimal = new Elephant(name, "Plants", "Herbivore");
        else if(type.equalsIgnoreCase("Penguin"))
            newAnimal = new Penguin(name, "Plants", "Herbivore");
        else if(type.equalsIgnoreCase("Lion"))
            newAnimal = new Lion(name, "Meat", "carnivore");
        else if(type.equalsIgnoreCase("Tiger"))
            newAnimal = new Tiger(name, "Meat", "carnivore");
        return  newAnimal;
    }
}
