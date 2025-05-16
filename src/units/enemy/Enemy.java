package units.enemy;

import javafx.scene.image.ImageView;
import units.defender.Defender;


public abstract class Enemy {
    private int speed;
    private int hp;
    float xPos;
    float yPos;
    private int row;
    private boolean isAttacking;


    public int getHp() {
        return hp;
    }
    public void setHp(int newHp){
        this.hp = newHp;
    }
    public abstract void move(float speed);
    public abstract float getxPos();
    public abstract float getyPos();
    public abstract float getSpeed();
    public int getRow(){
        return row;
    }

    public void attack(Defender defender) {
    }
    public void setIsAttacking(Boolean bol){
        this.isAttacking = bol;
    }
    public boolean getIsAttacking(){
        return isAttacking;
    }

    public abstract ImageView getSpriteView();

    public abstract Object getCurrentImage();

//    public abstract Image getCurrentFrameImg();

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }
}
