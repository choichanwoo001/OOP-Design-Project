package RPG_oop;

public class EpicMonster extends Monster{ // 회피율 1/5
    public EpicMonster(int hp, int attack) {
        super(hp, attack, 2);
    }
    // skillSet은 Normal, Epic 다르긴 해야함
}
