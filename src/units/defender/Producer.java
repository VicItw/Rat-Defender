package units.defender;

import game.Soul;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import units.logic.Producable;
//import game.GameScreenUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Producer extends Defender implements Producable {
    private int damage;
    private int row;
    private int column;
    private int health;
    private int cost = 50;
    private int produceAmount = 10;
    private AnimationTimer animationTimer;
    private Image spriteSheet;
    private ImageView spriteView;
    private final int TOTAL_FRAMES = 8; // Total number of frames in the sprite sheet
    private final int FRAME_WIDTH = 950; // Width of each frame in pixels
    private final int FRAME_HEIGHT = 950; // Height of each frame in pixels
    private final double FRAME_DURATION = 0.1; // Duration of each frame in milliseconds **seconds
    private int currentFrameX ;


    public Producer(int row, int column){
        this.row = row;
        this.column = column;
        this.health = 50;
        this.damage = 0;
        // Load the sprite sheet
        spriteSheet = new Image(Producer.class.getResourceAsStream("/producerSpriteSheet.png"));
        spriteView = new ImageView(spriteSheet);
        spriteView.setViewport(new Rectangle2D(0, 0, 1024, 1640));
        spriteView.setFitHeight(128);
        spriteView.setPreserveRatio(true);


        // Set up the animation timer
        animationTimer = new AnimationTimer() {
            private long lastUpdateTime = 0;
            private int currentFrame = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdateTime >= FRAME_DURATION * 1_000_000_000.0) {
                    currentFrameX = currentFrame * FRAME_WIDTH;
                    int y = 0;
                    spriteView.setViewport(new Rectangle2D(currentFrameX, y, FRAME_WIDTH, FRAME_HEIGHT));
                    spriteView.setFitHeight(128);
                    spriteView.setPreserveRatio(true);
                    lastUpdateTime = now;
                    currentFrame = (currentFrame + 1) % TOTAL_FRAMES;
                }
            }
        };
    }


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    public int getCost() {
        return cost;
    }
    public ImageView getSpriteView() {
        return spriteView;
    }

    public void produce(Soul soul){
        soul.setAmount(soul.getAmount()+produceAmount);
        animationTimer.start(); // Start the animation
    }
}
