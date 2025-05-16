package units.defender;

import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import units.enemy.Enemies;
import units.enemy.Enemy;
import units.logic.AttackRatable;
import game.GameScreenUtils;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.ArrayList;

public class Shooter extends Defender implements AttackRatable {
    private int damage;
    private int row;
    private int column;
    private int health;
    private int cost = 50;

    private Image spriteSheet;
    private ImageView spriteView;
    private int spriteWidth = 128;
    private int spriteHeight = 128;
    private int numFrames = 8;
    private int frameIndex = 0;
    private long lastUpdateTime = 0;
    private int currentFrame = 0;
    private final int TOTAL_FRAMES = 8; // Total number of frames in the sprite sheet
    private final int FRAME_WIDTH = 1200; // Width of each frame in pixels
    private final int FRAME_HEIGHT = 1200; // Height of each frame in pixels
    private final double FRAME_DURATION = 0.1; // Duration of each frame in milliseconds **seconds
    private int currentFrameX ;

    private AnimationTimer animationTimer;


    public Shooter(int row, int column) {
        this.row = row;
        this.column = column;
        this.damage = 1;
        this.health = 50;

        // Load the sprite sheet
        spriteSheet = new Image(Shooter.class.getResourceAsStream("/ShooterSpriteSheet.png"));
        spriteView = new ImageView(spriteSheet);
        spriteView.setViewport(new Rectangle2D(0, 0, FRAME_WIDTH, FRAME_HEIGHT));
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
//    public void playAnimation(Plant plant){
//        for (int i = 0; i < 8192;i++){
//            spriteView.setViewport(new Rectangle2D(i*1139, 0, 1139, 1650));
//            spriteView.setFitHeight(128);
//            spriteView.setPreserveRatio(true);
//        }
//    }

    public void attack(Enemies enemies) {
        ArrayList<Enemy> enemyRow = enemies.getEnemiesRowList().get(row);
        if (!enemyRow.isEmpty() && enemyRow.getFirst().getxPos() >= (column+1) * GameScreenUtils.getGridCellWidth()+170 && enemyRow.getFirst().getxPos() < 1280) {
            animationTimer.start();
            enemyRow.getFirst().setHp(enemyRow.get(0).getHp() - damage);
//            if (enemyRow.getFirst().getHp() <= 0){
//                enemyRow.remove(0);
//            }
             // Start the animation
//            System.out.println(currentFrameX);
        } else{
            animationTimer.stop(); // Stop the animation
        }
//        {
//
////            spriteView.setViewport(new Rectangle2D(0, 0, FRAME_WIDTH, FRAME_HEIGHT));
//        }
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

    public static ImageView draw() {
        Image spriteSheet = new Image(Shooter.class.getResourceAsStream("/ShooterSpriteSheet - Copy.png"));
        PixelReader pixelReader = spriteSheet.getPixelReader();
        WritableImage transparentImage = new WritableImage(pixelReader, (int) spriteSheet.getWidth(), (int) spriteSheet.getHeight());

        for (int y = 0; y < transparentImage.getHeight(); y++) {
            for (int x = 0; x < transparentImage.getWidth(); x++) {
                int argb = transparentImage.getPixelReader().getArgb(x, y);
                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;

                // Set the pixel to transparent if it's white
                if (r == 255 && g == 255 && b == 255) {
                    transparentImage.getPixelWriter().setArgb(x, y, 0);
                }
            }
        }

        ImageView sprite1 = new ImageView(transparentImage);
        sprite1.setViewport(new Rectangle2D(0, 0, 1139, 1650));
        sprite1.setFitHeight(128);
        sprite1.setPreserveRatio(true);
        return sprite1;
    }
}



