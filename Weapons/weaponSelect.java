package Weapons;

import javafx.scene.layout.Pane;

public class weaponSelect extends Pane implements java.io.Serializable {
    public weaponSelect sword;
    public weaponSelect bow;
    public weaponSelect dagger;
    public weaponSelect axe;

    public weaponSelect() {
        this.sword = sword;
        this.bow = bow;
        this.dagger = dagger;
        this.axe = axe;
    }

    public weaponSelect(weaponSelect sword, weaponSelect bow, weaponSelect dagger, weaponSelect axe) {
        sword = sword;
        bow = bow;
        dagger = dagger;
        axe = axe;
    }

    public weaponSelect getSword() { return sword; }

    public weaponSelect setSword(weaponSelect sword) { this.sword = sword; return sword; }

    public weaponSelect getBow() {
        return bow;
    }

    public weaponSelect setBow(weaponSelect bow) { this.bow = bow; return bow; }

    public weaponSelect getDagger() {
        return dagger;
    }

    public void setDagger(weaponSelect dagger) {
        this.dagger = dagger;
    }

    public weaponSelect getAxe() {
        return axe;
    }

    public void setAxe(weaponSelect axe) { this.axe = axe; }
}