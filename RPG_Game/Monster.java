package RPG_oop;

public class Monster {
    private int hp;
    private int attack;
    private int avoid; // 이거는 언제 초기화 해야할까?
//    Skills skill = new Skills();

    public Monster(int hp, int attack, int avoid) {
        this.hp = hp;
        this.attack = attack;
        this.avoid = avoid;
    }

    public int getHp() {return hp;}
    public void setHp(int hp) {this.hp = hp;}

    public int getAttack() {return attack;}
    public void setAttack(int attack) {this.attack = attack;}

    public int getAvoid() {return avoid;}
    public void setAvoid(int avoid) {this.avoid = avoid;}
}

