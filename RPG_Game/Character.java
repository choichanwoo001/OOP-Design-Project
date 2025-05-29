package RPG_oop;

import java.util.ArrayList;

public class Character {
    private int hp;
    private int attack;
    private int exp = 0; // 경험치
    private String weapon;
    private int level;
    private int avoid;
    Inventory bag = new Inventory();

    Character(){};

    public Character(int hp, int attack, String weapon) {
        this.hp = hp;
        this.attack = attack;
        this.weapon = weapon;
        this.avoid = 5;
    }

    public int getHp() {return hp;}
    public void setHp(int hp) {this.hp = hp;}

    public int getAttack() {return attack;}
    public void setAttack(int attack) {this.attack = attack;}

    public int getExp() {return exp;}
    public void setExp(int exp) {this.exp = exp;}

    public int getAvoid() {return avoid;}
    public void setAvoid(int avoid) {this.avoid = avoid;}
}

