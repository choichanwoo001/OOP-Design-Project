package RPG_oop;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EpicMonster Emonster = new EpicMonster(1000, 50);
        NormalMonster Nmonster = new NormalMonster(500, 25);
        Character character = null;
        Fight fight = new Fight();

        System.out.println("1. Archer/2. Knight/3. Mage");
        System.out.print("Choose the Character : "); int option = scanner.nextInt();
        if(option == 1){
            character = new Archer();
        }
        else if(option == 2){
            character = new Knight();
        }
        else if(option == 3){
            character = new Mage();
        }
        else{
            System.out.println("잘못 입력하셨습니다.");
        }

        fight.fight(Nmonster, character);
//        fight.fight(Emonster, character);
    }
}
