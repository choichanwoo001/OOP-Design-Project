package RPG_oop;

public class NormalMonster extends Monster{ // 회피율 1/10
    public NormalMonster(int hp, int attack) {
        super(hp, attack, 1);
    }
    // skillSet은 Normal, Epic 다르긴 해야함
}
