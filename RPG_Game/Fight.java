package RPG_oop;

public class Fight {
    void fight(Monster monster, Character character){
        String winner = "";
        while(true){
            if(monster.getHp() <= 0 || character.getHp() <= 0){
                winner = (monster.getHp() <= 0 ? "User" : "Monster");
                break;
            }

            int Mpossible = 10/monster.getAvoid();
            int MgetAvoid = (int)(Math.random()*(Mpossible));
            if(MgetAvoid == Mpossible-1)
                continue;
            else {
                monster.setHp(monster.getHp() - character.getAttack());
                System.out.println("몬스터의 현재 Hp : " + monster.getHp());
            }
//--------------------------------------------------------------------------//
            int Cpossible = 10/character.getAvoid();
            int CgetAvoid = (int)(Math.random()*(Cpossible));
            if(CgetAvoid == Cpossible - 1){
                System.out.println("사용자가 몬스터의 공격을 회피하였습니다.");
                continue;
            }
            else {
                character.setHp(character.getHp() - monster.getAttack());
                System.out.println("사용자의 현재 Hp : " + character.getHp());
            }
            System.out.println();
        }
        System.out.println(winner);
        System.out.println();
    }
}
