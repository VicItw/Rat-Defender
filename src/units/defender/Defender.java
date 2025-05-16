package units.defender;

import units.enemy.Enemies;
import javafx.scene.image.ImageView;

abstract public class Defender {
    private int health;
    private int row;
    private int column;

    public static ImageView draw() {
        return null;
    }

    public void attack(Enemies enemies) {
    }
    ;
    public int getRow(){
        return  row;
    }
    public int getColumn() {
        return column;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

