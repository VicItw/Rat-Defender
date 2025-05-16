//package units.enemy;
//
//import javafx.animation.AnimationTimer;
//import javafx.geometry.Rectangle2D;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.image.WritableImage;
//import units.Plant.Plant;
//import units.Plant.Shooter;
//import units.logic.Movable;
//import game.GameScreenUtils;
//import javafx.scene.shape.Rectangle;
//
//public class BasicEnemy extends Enemy implements Movable {
//    private final float speed = 3;
//    private int row;
//    private int damage = 1;
//    private boolean isAttacking;
//    private Image spriteSheet;
//    private ImageView spriteView;
//    private int FRAME_WIDTH = 1500;
//    private int FRAME_HEIGHT = 1500;
//    private AnimationTimer animationTimer;
//    private int FRAME_DURATION = 1;
//    private int currentFrameX;
//    private int TOTAL_FRAMES = 7;
//    private Image currentFrameImg;
//    public ImageView getSpriteView() {
//        return spriteView;
//    }
//    public BasicEnemy(float x,int row){
//        xPos = x;
//        yPos = 150 + row* GameScreenUtils.getGridCellHeight();
//        this.row = row;
//        this.hp = 10;
//        isAttacking = false;
//        spriteSheet = new Image(BasicEnemy.class.getResourceAsStream("/enemySpriteSheet.png"));
//        spriteView = new ImageView(spriteSheet);
//        spriteView.setViewport(new Rectangle2D(0, 0, FRAME_WIDTH, FRAME_HEIGHT));
//        spriteView.setFitHeight(128);
//        spriteView.setPreserveRatio(true);
//
//
//        // Set up the animation timer
//        animationTimer = new AnimationTimer() {
//            private long lastUpdateTime = 0;
//            private int currentFrame = 0;
//
//            @Override
//            public void handle(long now) {
//                if (now - lastUpdateTime >= FRAME_DURATION * 1_000_000_000.0) {
//                    currentFrameX = currentFrame * FRAME_WIDTH;
//                    int y = 0;
//                    BasicEnemy.this.currentFrameImg = new WritableImage(spriteSheet.getPixelReader(), currentFrameX, y, FRAME_WIDTH, FRAME_HEIGHT);
//                    lastUpdateTime = now;
//                    currentFrame = (currentFrame + 1) % TOTAL_FRAMES;
//                    spriteView.setFitHeight(128);
//                    spriteView.setPreserveRatio(true);
//                    currentFrameImg = spriteView.getImage();
//                }
//            }
//        };
//    }
//    public Rectangle draw(){
//        return new Rectangle(20,20);
//    }
//    @Override
//    public void move(float speed){
//        this.xPos -= speed;
//        animationTimer.start();
//    }
//
//    public float getSpeed() {
//        return speed;
//    }
//    @Override
//    public float getxPos(){
//        return xPos;
//    }
//    @Override
//    public float getyPos(){
//        return yPos;
//    }
//    private int hp;
//
//    @Override
//    public int getHp() {
//        return hp;
//    }
//    @Override
//    public void setHp(int hp){
//        this.hp = hp;
//    }
//    @Override
//    public int getRow(){
//        return row;
//    }
//    public void attack(Plant plant){
//        plant.setHealth(plant.getHealth()-damage);
//    }
//    public void setIsAttacking(Boolean bol){
//        this.isAttacking = bol;
//    }
//    public boolean getIsAttacking(){
//        return isAttacking;
//    }
//    public Image getCurrentFrameImg() {
//        return currentFrameImg;
//    }
//    public Image getSpriteSheet(){
//        return spriteSheet;
//    }
//
//}
//
//
//
package units.enemy;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import units.defender.Defender;
import game.GameScreenUtils;

import java.util.ArrayList;

public class BasicEnemy extends Enemy {
    private final float speed = 3;
    private int row;
    private int damage = 1;
    private boolean isAttacking;
    private Image spriteSheet;
    private ImageView spriteView;
    private AnimationTimer animationTimer;
    private int FRAME_DURATION = 20;
    private int TOTAL_FRAMES = 7;
    private Image currentImage;
    private ArrayList<Image> frameList;
    public ImageView getSpriteView() {
        return spriteView;
    }
    public BasicEnemy(float x,int row){
        xPos = x;
        yPos = 150 + row* GameScreenUtils.getGridCellHeight();
        this.row = row;
        this.hp = 10;
        isAttacking = false;
        frameList = new ArrayList<Image>();
        Image frame1 = new Image(BasicEnemy.class.getResourceAsStream("/enemyFrame1.png"));
        Image frame2 = new Image(BasicEnemy.class.getResourceAsStream("/enemyFrame2.png"));
        Image frame3 = new Image(BasicEnemy.class.getResourceAsStream("/enemyFrame3.png"));
        Image frame4 = new Image(BasicEnemy.class.getResourceAsStream("/enemyFrame4.png"));
        Image frame5 = new Image(BasicEnemy.class.getResourceAsStream("/enemyFrame5.png"));
        Image frame6 = new Image(BasicEnemy.class.getResourceAsStream("/enemyFrame6.png"));
        Image frame7 = new Image(BasicEnemy.class.getResourceAsStream("/enemyFrame7.png"));
        frameList.add(frame1);
        frameList.add(frame2);
        frameList.add(frame3);
        frameList.add(frame4);
        frameList.add(frame5);
        frameList.add(frame6);
        frameList.add(frame7);
        currentImage = frame1;

        // Set up the animation timer
        animationTimer = new AnimationTimer() {
            private long lastUpdateTime = 0;
            private int currentFrame = 0;


            @Override
            public void handle(long now) {
                if (now - lastUpdateTime >= FRAME_DURATION * 1_000_000) {
                    currentImage = frameList.get(currentFrame);
                    currentFrame +=1;
                    if (currentFrame == 6){
                        currentFrame =0;
                    }
                }
            }
        };
    }
//    public Rectangle draw(){
//        return new Rectangle(10,10);
//    }
    @Override
    public void move(float speed){
        if (!this.isAttacking) {
            this.xPos -= speed;
            animationTimer.start();
        }
        else animationTimer.stop();
    }

    public float getSpeed() {
        return speed;
    }
    @Override
    public float getxPos(){
        return xPos;
    }
    @Override
    public float getyPos(){
        return yPos;
    }
    private int hp;

    @Override
    public int getHp() {
        return hp;
    }
    @Override
    public void setHp(int hp){
        this.hp = hp;
    }
    @Override
    public int getRow(){
        return row;
    }
    public void attack(Defender defender){
        defender.setHealth(defender.getHealth()-damage);
    }
    public void setIsAttacking(Boolean bol){
        this.isAttacking = bol;
    }
    public boolean getIsAttacking(){
        return isAttacking;
    }

    public Image getCurrentImage(){
        return currentImage;
    }
}




