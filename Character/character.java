package Character;

import Weapons.weaponSelect;

public class character implements java.io.Serializable {
    public String ch;
    public int health;
    public int atkpw;
    public int atksp;
    public int str;
    private Weapons.weaponSelect wp;

    public character() {
        this.ch = ch;
        health = 10;
        atkpw = 2;
        atksp = 2;
        str = 10;
        wp = new weaponSelect();
    }

    public character(String ch, int health, int atkpw, int atksp, int str, Weapons.weaponSelect wp) {
        this.ch = ch;
        this.health = health;
        this.atkpw = atkpw;
        this.atksp = atksp;
        this.str = str;
        this.wp = wp;
    }

    public weaponSelect getWp() {
        return wp;
    }

    public void setWp(weaponSelect wp) {
        this.wp = wp;
    }

    public String setCharacter(String ch) {
        return ch;
    }

    public String getCharacter() {
        return ch;
    }

    public int getAtkpw() {
        return atkpw;
    }

    public void setAtkpw(int atkpw) {
        this.atkpw = atkpw;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getAtksp() {
        return atksp;
    }

    public void setAtksp(int atksp) {
        this.atksp = atksp;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
