package game;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import units.defender.*;
import units.enemy.BasicEnemy;
import units.enemy.Enemies;
import units.enemy.Enemy;
import units.logic.Producable;
import units.logic.AttackRatable;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

public class GameScreen {
    private Stage stage;
    private VBox vbox;
    private GridPane gridPane;
    private Scene scene;
    private HBox topBar;
    private boolean shooterSelected;
    private boolean producerSelected;
    private boolean meleeSelected;
    private ToggleButton shooterButton;
    private ToggleButton producerButton;
    private ToggleButton meleeButton;
//    private StackPane root;
    private Enemies enemies;
    private PlacedDefenders placedDefenders;
    private Soul soul;
    private Label soulLabel;
    private ToggleGroup defenderBar;
    private StackPane middleBox;


    public GameScreen(Stage stage){
        this.stage = stage;
        buildUI();
    }
    private void buildUI(){
        vbox = new VBox();
        middleBox = new StackPane();
        gridPane = new GridPane();
        defenderBar = new ToggleGroup();

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPrefWidth(GameScreenUtils.getGridCellWidth());

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPrefHeight(GameScreenUtils.getGridCellHeight());


        for (int i = 0; i < GameScreenUtils.getColAmount(); i++) {
            gridPane.getColumnConstraints().add(columnConstraints);
        }
        for (int j = 0; j < GameScreenUtils.getRowAmount(); j++) {
            gridPane.getRowConstraints().add(rowConstraints);
        }

        for (int col = 0; col < GameScreenUtils.getColAmount(); col++) {
            for (int row = 0; row < GameScreenUtils.getRowAmount(); row++) {
                Pane cell = new Pane();
//                cell.setStyle("-fx-background-color: lightblue; -fx-border-color: white;");
                cell.setStyle("-fx-border-color: rgba(211, 211, 211, 0.1);");
                gridPane.add(cell, col, row);
                int finalRow = row;
                int finalCol = col;
                cell.setOnMouseClicked(event -> handleCellClick(cell,finalRow,finalCol));
            }
        }
//        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.TOP_LEFT);



        topBar = new HBox();
        topBar.setPrefHeight(100);

        shooterButton = new ToggleButton();
        Image img = new Image(GameScreen.class.getResourceAsStream("/shooterLogo.png"));
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(100);
        imgView.setPreserveRatio(true);
        shooterButton.setGraphic(imgView);
        shooterButton.setBackground(new Background(new BackgroundFill(Color.web("0xA93F3F", 1.0),null,null)));
//        shooterButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                shooterButton.setBackground(new Background(new BackgroundFill(Color.web("0x631D1D", 1.0),null,null)));
//                shooterSelected = true;
//
//            }
//        });
        shooterButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                shooterButton.setBackground(new Background(new BackgroundFill(Color.web("0x852E2E", 1.0),null,null)));
            }
        });
        shooterButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!shooterSelected) {
                    shooterButton.setBackground(new Background(new BackgroundFill(Color.web("0xA93F3F", 1.0), null, null)));
                }
            }
        });
        shooterButton.setPrefHeight(102);
        shooterButton.setPrefWidth(102);
        shooterButton.setToggleGroup(defenderBar);

        soul = new Soul(100);
        soulLabel = new Label("Soul:"+ soul.getAmount());
        Text moneyText = new Text("Soul:"+ soul.getAmount());
//        money.setShape(polygon);
//        money.setBackground(new Background(new BackgroundFill(Color.GRAY,null,null)));
        soulLabel.setBackground(new Background(new BackgroundFill(Color.GRAY,null,null)));
        soulLabel.setFont(Font.font("Inter",30));

        soulLabel.setPrefWidth(130);
        soulLabel.setPrefHeight(70);
        topBar.getChildren().add(soulLabel);
        topBar.setMargin(soulLabel,new Insets(0,0,10,10));

        topBar.getChildren().add(shooterButton);
        topBar.setMargin(shooterButton,new Insets(0,10,0,10));
        producerButton = new ToggleButton();
        Image img2 = new Image(GameScreen.class.getResourceAsStream("/producerLogo.png"));
        ImageView imgView2 = new ImageView(img2);
//        imgView.setViewport(new Rectangle2D(0,0,1024,1640));
        imgView2.setFitHeight(100);
        imgView2.setPreserveRatio(true);
        producerButton.setGraphic(imgView2);
        producerButton.setBackground(new Background(new BackgroundFill(Color.web("0xA93F3F", 1.0),null,null)));
//        producerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                producerButton.setBackground(new Background(new BackgroundFill(Color.web("0x631D1D", 1.0),null,null)));
//                producerSelected = true;
//
//            }
//        });
        producerButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                producerButton.setBackground(new Background(new BackgroundFill(Color.web("0x852E2E", 1.0),null,null)));
            }
        });
        producerButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!producerSelected) {
                    producerButton.setBackground(new Background(new BackgroundFill(Color.web("0xA93F3F", 1.0), null, null)));
                }
            }
        });
        producerButton.setPrefHeight(102);
        producerButton.setPrefWidth(102);
//        producerButton.setShape(polygon);
        producerButton.setToggleGroup(defenderBar);
        topBar.getChildren().add(producerButton);
        topBar.setMargin(producerButton,new Insets(0,10,0,0));

        meleeButton = new ToggleButton();
        meleeButton.setPrefWidth(102);
        meleeButton.setPrefHeight(102);
        Image img3 = new Image(GameScreen.class.getResourceAsStream("/meleeAttackerLogo.png"));
        ImageView imgView3 = new ImageView(img3);
        imgView3.setFitHeight(100);
        imgView3.setPreserveRatio(true);
        meleeButton.setGraphic(imgView3);
        meleeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                meleeButton.setBackground(new Background(new BackgroundFill(Color.web("0x852E2E", 1.0),null,null)));
            }
        });
        meleeButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!meleeSelected) {
                    meleeButton.setBackground(new Background(new BackgroundFill(Color.web("0xA93F3F", 1.0), null, null)));
                }
            }
        });

        meleeButton.setBackground(new Background(new BackgroundFill(Color.web("0xA93F3F", 1.0),null,null)));
        meleeButton.setToggleGroup(defenderBar);
        topBar.getChildren().add(meleeButton);
        topBar.setMargin(producerButton,new Insets(0,10,0,0));

        middleBox.getChildren().add(gridPane);
        gridPane.setPadding(new Insets(0,0,0,170));

        vbox.getChildren().add(topBar);
        vbox.getChildren().add(middleBox);

        Image background = new Image(GameScreen.class.getResourceAsStream("/gamescreenbg.png"));
        ImageView backgroundView = new ImageView(background);
        backgroundView.setFitWidth(1280);
        backgroundView.setFitHeight(800);
        backgroundView.setPreserveRatio(true);

        shooterButton.selectedProperty().addListener((ov, t, t1) -> {
            if(t1 && soul.getAmount()>= 50)
            {
                shooterSelected = true;
                shooterButton.setBackground(new Background(new BackgroundFill(Color.web("0x631D1D", 1.0),null,null)));

            }
            if(t){
                shooterSelected = false;
            }
            if (t1 && soul.getAmount() < 50){
                shooterButton.setSelected(false);
            }

        });

        producerButton.selectedProperty().addListener((ov, t, t1) -> {
            if(t1 && soul.getAmount()>= 50)
            {
                producerButton.setBackground(new Background(new BackgroundFill(Color.web("0x631D1D", 1.0),null,null)));
                producerSelected = true;
            }
            if(t){
                producerSelected = false;
            }
            if (t1 && soul.getAmount() < 50){
                producerButton.setSelected(false);
            }

        });

        meleeButton.selectedProperty().addListener((ov, t, t1) -> {
            if(t1 && soul.getAmount()>= 50)
            {
                meleeButton.setBackground(new Background(new BackgroundFill(Color.web("0x631D1D", 1.0),null,null)));
                meleeSelected = true;
            }
            if(t){
                meleeSelected = false;
            }
            if (t1 && soul.getAmount() < 50){
                meleeButton.setSelected(false);
            }

        });

        enemies = new Enemies();

            BasicEnemy enemy = new BasicEnemy(1280,new Random().nextInt(5));
            BasicEnemy enemy2 = new BasicEnemy(1800 ,new Random().nextInt(5));
            BasicEnemy enemy3 = new BasicEnemy(1600,new Random().nextInt(5));
            BasicEnemy enemy4 = new BasicEnemy(1900,new Random().nextInt(5));
            BasicEnemy enemy5 = new BasicEnemy(2100,new Random().nextInt(5));
        BasicEnemy enemy6 = new BasicEnemy(2800 ,new Random().nextInt(5));
        BasicEnemy enemy7 = new BasicEnemy(2700,new Random().nextInt(5));
        BasicEnemy enemy8 = new BasicEnemy(3000,new Random().nextInt(5));
        BasicEnemy enemy9= new BasicEnemy(2500,new Random().nextInt(5));
        BasicEnemy enemy10= new BasicEnemy(2800,new Random().nextInt(5));
        BasicEnemy enemy11= new BasicEnemy(2900,new Random().nextInt(5));
//        BasicEnemy enemy12= new BasicEnemy(2700,1);
//        BasicEnemy enemy13= new BasicEnemy(3000,0);
//        BasicEnemy enemy14= new BasicEnemy(8000,2);
//        BasicEnemy enemy15= new BasicEnemy(7500,2);
//        BasicEnemy enemy16= new BasicEnemy(9000,0);
//        BasicEnemy enemy17= new BasicEnemy(8000,3);
//        BasicEnemy enemy18= new BasicEnemy(7800,3);

//        enemies.getEnemiesRowList().get(0).add(enemy);
//        enemies.getEnemiesRowList().get(1).add(enemy2);
//        enemies.getEnemiesRowList().get(2).add(enemy3);
//        enemies.getEnemiesRowList().get(3).add(enemy4);
//        enemies.getEnemiesRowList().get(4).add(enemy5);
            enemies.getEnemiesRowList().get(enemy.getRow()).add(enemy);
            enemies.getEnemiesRowList().get(enemy2.getRow()).add(enemy2);
            enemies.getEnemiesRowList().get(enemy3.getRow()).add(enemy3);
            enemies.getEnemiesRowList().get(enemy4.getRow()).add(enemy4);
            enemies.getEnemiesRowList().get(enemy5.getRow()).add(enemy5);
        enemies.getEnemiesRowList().get(enemy6.getRow()).add(enemy6);
        enemies.getEnemiesRowList().get(enemy7.getRow()).add(enemy7);
        enemies.getEnemiesRowList().get(enemy8.getRow()).add(enemy8);
        enemies.getEnemiesRowList().get(enemy9.getRow()).add(enemy9);
        enemies.getEnemiesRowList().get(enemy10.getRow()).add(enemy10);
        enemies.getEnemiesRowList().get(enemy11.getRow()).add(enemy11);
//        enemies.getEnemiesRowList().get(enemy12.getRow()).add(enemy12);
//        enemies.getEnemiesRowList().get(enemy13.getRow()).add(enemy13);
//        enemies.getEnemiesRowList().get(enemy14.getRow()).add(enemy14);
//        enemies.getEnemiesRowList().get(enemy15.getRow()).add(enemy15);
//        enemies.getEnemiesRowList().get(enemy16.getRow()).add(enemy16);
//        enemies.getEnemiesRowList().get(enemy17.getRow()).add(enemy17);
//        enemies.getEnemiesRowList().get(enemy18.getRow()).add(enemy18);



        placedDefenders = new PlacedDefenders();

        Canvas canvas = new Canvas(1280,800);
        canvas.setMouseTransparent(true);
        GraphicsContext gc = canvas.getGraphicsContext2D();

//        gc.drawImage(ene);
//        gc.fillRect(enemy.getxPos(), enemy.getyPos(), rectangle.getWidth(), rectangle.getHeight());
        StackPane root = new StackPane();

        Thread moveThread = new Thread(() -> {
            try {
                while (!Thread.interrupted()) {
                    for (int row = 0; row < GameScreenUtils.getRowAmount(); row++) {
                        ArrayList<Enemy> enemyInRow = enemies.getEnemiesRowList().get(row);
                        ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
                        ArrayList<Defender> plantsInRow = placedDefenders.getDefendersRowList().get(row);
                        if(enemies.getEnemiesRow0().isEmpty()&&enemies.getEnemiesRow1().isEmpty() && enemies.getEnemiesRow2().isEmpty() && enemies.getEnemiesRow3().isEmpty() && enemies.getEnemiesRow4().isEmpty()){
//                            System.out.println("You Win");
                            try {
                                // Delay for 1 second (1000 milliseconds)
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                // Handle the interrupted exception if needed
//                                e.printStackTrace();
                            }
                            Platform.runLater(()->{
                                enemies.getEnemiesRowList().clear();
                                enemyInRow.clear();
                                placedDefenders.getDefendersRowList().clear();
                                new WinScreen(stage);

                            });
                        }
                        if (enemyInRow != null) {
                            // Update the position of all enemies in the current row
                            for (Enemy ratInRow : enemyInRow) {
                                // Save the previous position of the enemy
                                Platform.runLater(() -> {
                                    if (ratInRow.getxPos() <= 0) {
//                                    System.out.println(enemyy);
                                        enemies.getEnemiesRowList().clear();
                                        enemyInRow.clear();
                                        placedDefenders.getDefendersRowList().clear();
                                        new LoseScreen(stage);
//                                stage.setScene(new Scene(new Label("You lost")));
//                                    LoseScreen loseScreen = new LoseScreen(stage);
//                                    stage.setScene(loseScreen.giveScene(stage));
//                                    Thread.interrupt();
                                    }
                                });


                                double prevX = ratInRow.getxPos();
                                double prevY = ratInRow.getyPos();

                                    ratInRow.move(ratInRow.getSpeed()); // Call move method on each enemy


                                // Clear only the previous position of the enemy
                                if (prevX < 1280) { // Check if the canvas is still part of the scene graph
                                    gc.clearRect(prevX, prevY, 100, 100);
                                }
                                ArrayList<Defender> plantsInRowCopy = new ArrayList<>(plantsInRow);
                                // Check if the enemy has reached a plant in the same row
                                for (Defender plant : plantsInRowCopy) {
                                    if (ratInRow.getRow() == plant.getRow() && ratInRow.getxPos() < (plant.getColumn() + 1) * GameScreenUtils.getGridCellWidth()+170 - 5 && ratInRow.getxPos() > (plant.getColumn()) * GameScreenUtils.getGridCellWidth()+170) {
                                        // Remove the plant from the game
                                        ratInRow.attack(plant);
                                        ratInRow.setIsAttacking(true);
                                        if (plant.getHealth() <= 0) {
                                            placedDefenders.removeDeadDefender(plant, row);
                                            int cellIndex = (row + plant.getColumn() * 5);
                                            Pane cell = (Pane) gridPane.getChildren().get(cellIndex);

                                            // Wrap the code that modifies the scene graph within Platform.runLater()
                                            Platform.runLater(() -> {
                                                cell.setStyle("-fx-border-color: rgba(211, 211, 211, 0.1);");
                                                cell.getChildren().clear(); // Clear the cell's children to remove the plant
                                            });

                                            ratInRow.setIsAttacking(false);
                                        }
                                    }
                                }

                                // Add the dead enemies to the enemiesToRemove list
                                if (ratInRow.getHp() <= 0) {
                                    enemiesToRemove.add(ratInRow);
                                } else {
                                    // Draw the enemy at the new position

//                                    gc.fillRect(enemyy.getxPos(), enemyy.getyPos(), rectangle.getWidth(), rectangle.getHeight());
                                    Platform.runLater(() -> {
                                        BasicEnemy basicEnemy = (BasicEnemy) ratInRow;
                                            if (ratInRow.getxPos() <= 1280){
                                            gc.drawImage(((BasicEnemy) ratInRow).getCurrentImage(), ratInRow.getxPos(), ratInRow.getyPos(),100,100);
                                        }

                                    });

//                                    Producers producer = new Producers(row, column);
//                                    placedPlants.addPlant(producer, row, column);
//                                    producer.getSpriteView().setTranslateX(0);
//                                    producer.getSpriteView().setTranslateY(0);
//                                    producer.getSpriteView().setScaleX(1);
//                                    producer.getSpriteView().setScaleY(1);
//                                    cell.getChildren().add(producer.getSpriteView());

                                }
                            }

                            // Remove the dead enemies from the current row
                            for (Enemy enemyyy : enemiesToRemove) {
                                enemies.removeDeadEnemies(enemyInRow, enemyyy);
                            }
                        }

                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
//                        e.printStackTrace();
                        }
                    }
                }
            }catch (IndexOutOfBoundsException e){

            }
        });
        moveThread.setDaemon(true);
        moveThread.start();

        Thread attackThread = new Thread(() -> {
            try {
                try {
                    while (!Thread.interrupted() && !placedDefenders.getDefendersRowList().isEmpty()) {
                        ArrayList<ArrayList<Defender>> plantsRowListCopy = new ArrayList<>(placedDefenders.getDefendersRowList());
                        for (ArrayList<Defender> plantRow : plantsRowListCopy) {
                            for (Defender plant : plantRow) {
                                if (plant instanceof AttackRatable) {
                                    plant.attack(enemies);

                                }
                            }
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException e) {

                }
            }catch (NoSuchElementException e){

            }
        });

        attackThread.setDaemon(true); // Set the thread as a daemon so it stops when the application exits
        attackThread.start(); // Start the thread

        Thread soulThread = new Thread(()->{
            try{while(!Thread.interrupted()) {
                for (int row = 0; row < GameScreenUtils.getRowAmount(); row++) {
                    for (Defender plant : placedDefenders.getDefendersRowList().get(row)) {
                        if (plant instanceof Producable) {
                            ((Producable) plant).produce(soul);
                            updateSoulLabel();
                        }
                    }
                }
                try {
                    Thread.sleep(2300);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }}catch (IndexOutOfBoundsException e){

            }
        });
        soulThread.start();


//        Thread loseThread = new Thread(() -> {
//            while (true) {
//                for (int row = 0; row < GameScreenUtils.getRowAmount(); row++) {
//                    ArrayList<Enemy> enemyInRow = enemies.getEnemiesRowList().get(row);
//                    ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
//                    ArrayList<Plant> plantsInRow = placedPlants.getPlantsRowList().get(row);
//
//                    if (enemyInRow != null) {
//                        // Update the position of all enemies in the current row
//                        for (Enemy enemyy : enemyInRow) {
//                            // Save the previous position of the enemy
//                            Platform.runLater(() -> {
//                                if (enemyy.getxPos() <= 0) {
//                                    enemies.getEnemiesRowList().clear();
//                                    enemyInRow.clear();
//                                    enemiesToRemove.clear();
//                                    placedPlants.getPlantsRowList().clear();
//
//                                    new LoseScreen(stage);
//
//                                }
//                            });





//        Thread moneyThread = new Thread(()->{
//            Platform.runLater(() -> {
//                money.setText("Money: " + moneyAmount.getAmount());
//                topBar.getChildren().add(money);
//            });
//
//
//        });
//        moneyThread.start();




        root.getChildren().add(backgroundView);
        root.getChildren().add(vbox);
        root.getChildren().add(canvas);

        scene = new Scene(root);
//        shooterButton.setShape(polygon);
        stage.setScene(scene);
        stage.setMinHeight(800);
        stage.setMinWidth(1280);
        stage.show();
    }
    private void handleCellClick(Pane grid, int row, int column) {
//        System.out.println("Clicked on cell: " + row + ", " + column);

        if (shooterSelected && soul.getAmount() >= 50 && !gridPane.getChildren().isEmpty()) {
            int cellIndex = (row + column * 5);

                Pane cell = (Pane) gridPane.getChildren().get(cellIndex);
//            cell.setStyle("-fx-background-color: red; -fx-border-color: white;");
                cell.setStyle("-fx-border-color: rgba(211, 211, 211, 0.1);");

            if (cell.getChildren().isEmpty()) {
                Shooter shooter = new Shooter(row, column);
                placedDefenders.addDefender(shooter, row, column);
                shooter.getSpriteView().setTranslateX(0);
                shooter.getSpriteView().setTranslateY(0);
                shooter.getSpriteView().setScaleX(1);
                shooter.getSpriteView().setScaleY(1);
                cell.getChildren().add(shooter.getSpriteView());

                soul.reduceMoney(shooter.getCost());

                updateSoulLabel();
            }
            shooterButton.setSelected(false);
            shooterButton.setBackground(new Background(new BackgroundFill(Color.web("0xA93F3F", 1.0), null, null)));


        }
        if (producerSelected && soul.getAmount() >= 50) {

                int cellIndex = (row + column * 5);
                Pane cell = (Pane) gridPane.getChildren().get(cellIndex);
//            cell.setStyle("-fx-background-color: red; -fx-border-color: white;");
                cell.setStyle("-fx-border-color: rgba(211, 211, 211, 0.1);");

                if (cell.getChildren().isEmpty()) {
                    Producer producer = new Producer(row, column);
                    placedDefenders.addDefender(producer, row, column);
                    producer.getSpriteView().setTranslateX(0);
                    producer.getSpriteView().setTranslateY(0);
                    producer.getSpriteView().setScaleX(1);
                    producer.getSpriteView().setScaleY(1);

                    cell.getChildren().add(producer.getSpriteView());

                    soul.reduceMoney(producer.getCost());

                    updateSoulLabel();
                }
                producerButton.setSelected(false);
                producerButton.setBackground(new Background(new BackgroundFill(Color.web("0xA93F3F", 1.0), null, null)));
        }
        if (meleeSelected && soul.getAmount() >= 50 && !gridPane.getChildren().isEmpty()) {
            int cellIndex = (row + column * 5);

            Pane cell = (Pane) gridPane.getChildren().get(cellIndex);
//            cell.setStyle("-fx-background-color: red; -fx-border-color: white;");
            cell.setStyle("-fx-border-color: rgba(211, 211, 211, 0.1);");

            if (cell.getChildren().isEmpty()) {
                MeleeAttacker meleeAttacker = new MeleeAttacker(row, column);
                placedDefenders.addDefender(meleeAttacker, row, column);
                meleeAttacker.getSpriteView().setTranslateX(0);
                meleeAttacker.getSpriteView().setTranslateY(0);
                meleeAttacker.getSpriteView().setScaleX(1);
                meleeAttacker.getSpriteView().setScaleY(1);
                cell.getChildren().add(meleeAttacker.getSpriteView());

                soul.reduceMoney(meleeAttacker.getCost());
//                System.out.println(moneyAmount.getAmount());
                updateSoulLabel();
            }
            meleeButton.setSelected(false);
            meleeButton.setBackground(new Background(new BackgroundFill(Color.web("0xA93F3F", 1.0), null, null)));


        }
    }
    private void updateSoulLabel() {
        Platform.runLater(() -> {

//            Label money = new Label("Money : "+ moneyAmount.getAmount()) ;
//                    (Label) topBar.getChildren().get(topBar.getChildren().size() - 1);
            if (soulLabel != null) {
                soulLabel.setText("Soul: " + soul.getAmount());

            }
        });
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public VBox getVbox() {
        return vbox;
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public HBox getTopBar() {
        return topBar;
    }

    public void setTopBar(HBox topBar) {
        this.topBar = topBar;
    }

    public boolean isShooterSelected() {
        return shooterSelected;
    }

    public void setShooterSelected(boolean shooterSelected) {
        this.shooterSelected = shooterSelected;
    }

    public boolean isProducerSelected() {
        return producerSelected;
    }

    public void setProducerSelected(boolean producerSelected) {
        this.producerSelected = producerSelected;
    }

    public boolean isMeleeSelected() {
        return meleeSelected;
    }

    public void setMeleeSelected(boolean meleeSelected) {
        this.meleeSelected = meleeSelected;
    }

    public ToggleButton getShooterButton() {
        return shooterButton;
    }

    public void setShooterButton(ToggleButton shooterButton) {
        this.shooterButton = shooterButton;
    }

    public ToggleButton getProducerButton() {
        return producerButton;
    }

    public void setProducerButton(ToggleButton producerButton) {
        this.producerButton = producerButton;
    }

    public ToggleButton getMeleeButton() {
        return meleeButton;
    }

    public void setMeleeButton(ToggleButton meleeButton) {
        this.meleeButton = meleeButton;
    }

    public Enemies getEnemies() {
        return enemies;
    }

    public void setEnemies(Enemies enemies) {
        this.enemies = enemies;
    }

    public PlacedDefenders getPlacedDefenders() {
        return placedDefenders;
    }

    public void setPlacedDefenders(PlacedDefenders placedDefenders) {
        this.placedDefenders = placedDefenders;
    }

    public Soul getSoul() {
        return soul;
    }

    public void setSoul(Soul soul) {
        this.soul = soul;
    }

    public Label getSoulLabel() {
        return soulLabel;
    }

    public void setSoulLabel(Label soulLabel) {
        this.soulLabel = soulLabel;
    }

    public ToggleGroup getDefenderBar() {
        return defenderBar;
    }

    public void setDefenderBar(ToggleGroup defenderBar) {
        this.defenderBar = defenderBar;
    }

    public StackPane getMiddleBox() {
        return middleBox;
    }

    public void setMiddleBox(StackPane middleBox) {
        this.middleBox = middleBox;
    }
}

