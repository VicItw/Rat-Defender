package game;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Menu {
    private VBox aboutBox;
    private VBox creditBox;
    public Menu(Stage stage) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        buildUI(stage);
    }



    public void buildUI(Stage stage) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
    StackPane root = new StackPane();
    Button playButton = new Button();
    Text playText = new Text("Start");
    playText.setFont(Font.font("Inter", FontWeight.BOLD,55));
    playText.setStrokeWidth(5);
    playText.setFill(Color.WHITE);
    playButton.setGraphic(playText);
    playButton.setBackground(new Background(new BackgroundFill(Color.web("0x853232", 1.0),null,null)));


    playButton.setPrefWidth(393);
    playButton.setPrefHeight(93);
    playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            playButton.setBackground(new Background(new BackgroundFill(Color.web("0x591C1C", 1.0),null,null)));
//            if (isSoundOn) {
//                toggleSound();
//                backgroundSound.dispose();
//            }
            Thread thread = new Thread(()->{
                Platform.runLater(()->{
                    new GameScreen(stage);
                });
                });
            thread.start();
        }
    });
    playButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            playButton.setBackground(new Background(new BackgroundFill(Color.web("0x692222", 1.0),null,null)));
        }
    });
    playButton.setOnMouseExited(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            playButton.setBackground(new Background(new BackgroundFill(Color.web("0x853232", 1.0),null,null)));
        }
    });


    Button aboutButton = new Button();
    Text aboutText = new Text("About");
    aboutText.setFont(Font.font("Inter", FontWeight.BOLD,55));
    aboutText.setFill(Color.WHITE);
    aboutText.setStrokeWidth(5);
    aboutButton.setGraphic(aboutText);
    aboutButton.setBackground(new Background(new BackgroundFill(Color.web("0x853232", 1.0),null,null)));


    aboutButton.setPrefWidth(393);
    aboutButton.setPrefHeight(93);
    aboutButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            aboutButton.setBackground(new Background(new BackgroundFill(Color.web("0x591C1C", 1.0),null,null)));
            aboutBox.setDisable(false);
            aboutBox.setVisible(true);

        }
    });
    aboutButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            aboutButton.setBackground(new Background(new BackgroundFill(Color.web("0x692222", 1.0),null,null)));

        }
    });
    aboutButton.setOnMouseExited(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            aboutButton.setBackground(new Background(new BackgroundFill(Color.web("0x853232", 1.0),null,null)));
        }
    });

    Button creditButton = new Button();
    Text creditText = new Text("Credit");
    creditText.setFont(Font.font("Inter", FontWeight.BOLD,55));
    creditText.setFill(Color.WHITE);
    creditText.setStrokeWidth(5);
    creditButton.setGraphic(creditText);
    creditButton.setBackground(new Background(new BackgroundFill(Color.web("0x853232", 1.0),null,null)));


    creditButton.setPrefWidth(393);
    creditButton.setPrefHeight(93);
    creditButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            creditButton.setBackground(new Background(new BackgroundFill(Color.web("0x853232", 1.0),null,null)));
            creditBox.setDisable(false);
            creditBox.setVisible(true);
        }
    });
    creditButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            creditButton.setBackground(new Background(new BackgroundFill(Color.web("0x692222", 1.0),null,null)));
        }
    });
    creditButton.setOnMouseExited(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            creditButton.setBackground(new Background(new BackgroundFill(Color.web("0x853232", 1.0),null,null)));
        }
    });

    VBox vBox = new VBox();
    vBox.setAlignment(Pos.BASELINE_CENTER);

    Image background = new Image(Menu.class.getResourceAsStream("/mainScreenBackground.jpg"));
    ImageView backgroundView = new ImageView(background);
    backgroundView.setViewport(new Rectangle2D(0,0,1280,800));
    VBox bottomVbox = new VBox();
    bottomVbox.getChildren().add(playButton);
    bottomVbox.getChildren().add(aboutButton);
    bottomVbox.getChildren().add(creditButton);

    Label aboutPage = new Label("Rat Defender is a game that was inspired from the popular tower-defense game, “Plant Vs Zombies”. In Rat \nDefender you are playing as the witch doctors’ side where you want to protect yourselves from\n getting infected by the rats.");
    aboutPage.setPrefWidth(1000);
    aboutPage.setPrefHeight(600);
    aboutPage.setTextAlignment(TextAlignment.CENTER);
    aboutPage.setFont(Font.font("Inter",20));
    aboutPage.setBackground(new Background(new BackgroundFill(Color.web("0xC94E4E", 1.0),null,null)));
    HBox backBox = new HBox();
    Button backButton = new Button();
    Text backText = new Text("Back");
    backText.setFont(Font.font("Inter", FontWeight.BOLD,15));
    backText.setFill(Color.BROWN);
    backButton.setGraphic(backText);


    backButton.setFont(Font.font(20));
    backBox.getChildren().add(backButton);
    backBox.setAlignment(Pos.CENTER);
    aboutBox = new VBox();

//        aboutBox.getChildren().add(new Button("Back"));
    aboutBox.setAlignment(Pos.CENTER);
    aboutBox.setDisable(true);
    aboutBox.setVisible(false);
    aboutBox.getChildren().add(backBox);
    aboutBox.getChildren().add(aboutPage);

    Label creditPage = new Label("Inthawut Vongjitvuttikrai 6631365021");
    creditPage.setPrefWidth(1000);
    creditPage.setPrefHeight(600);
    creditPage.setTextAlignment(TextAlignment.CENTER);
    creditPage.setFont(Font.font("Inter",60));
    creditPage.setBackground(new Background(new BackgroundFill(Color.web("0xC94E4E", 1.0),null,null)));
//        HBox backBox2 = backBox;

    HBox backBox2 = new HBox();
    Button backButton2 = new Button();
    Text backText2 = new Text("Back");
    backText2.setFont(Font.font("Inter", FontWeight.BOLD,15));
    backText2.setFill(Color.BROWN);
    backButton2.setGraphic(backText2);


    backButton2.setFont(Font.font(20));
    backBox2.getChildren().add(backButton2);
    backBox2.setAlignment(Pos.CENTER);

    creditBox = new VBox();
    creditBox.setAlignment(Pos.CENTER);
    creditBox.setDisable(true);
    creditBox.setVisible(false);
    creditBox.getChildren().add(backBox2);
    creditBox.getChildren().add(creditPage);

    backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            aboutBox.setVisible(false);
            aboutBox.setDisable(true);
        }
    });
    backButton2.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            creditBox.setVisible(false);
            creditBox.setDisable(true);
        }
    });


    Text gameTitle = new Text("Rat Defender");
    gameTitle.setFont(Font.font("Inter", FontWeight.EXTRA_BOLD,115));
    gameTitle.setFill(Color.WHITE);
    gameTitle.setStroke(Color.web("0x853232", 1.0));
    gameTitle.setStrokeWidth(5);

    VBox textContainer = new VBox(gameTitle);
    textContainer.setAlignment(Pos.CENTER);
    textContainer.setMargin(gameTitle, new Insets(50));


    root.getChildren().add(backgroundView);
    vBox.getChildren().add(textContainer);



    Image soundIcon = new Image(Menu.class.getResourceAsStream("/soundIcon.png"));
    ImageView soundIconView = new ImageView(soundIcon);
    soundIconView.setPreserveRatio(true);soundIconView.setFitWidth(100);

//    VBox soundBox = new VBox();
//    soundBox.getChildren().add(soundIconView);
//    soundBox.setMargin(soundIconView,new Insets(20));
//    soundBox.setAlignment(Pos.BOTTOM_LEFT);
//    soundBox.setMouseTransparent(true);
    soundIconView.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            toggleSound();
        }
    });

    root.getChildren().add(vBox);

    bottomVbox.setMargin(playButton, new Insets(13));
    bottomVbox.setMargin(aboutButton, new Insets(13));
    bottomVbox.setMargin(creditButton, new Insets(13));
    bottomVbox.setAlignment(Pos.BOTTOM_CENTER);
    root.getChildren().add(bottomVbox);

    root.getChildren().add(aboutBox);
    root.getChildren().add(creditBox);
    root.getChildren().add(soundIconView);
    soundIconView.setTranslateX(-560);
    soundIconView.setTranslateY(300);

    if(backgroundSound!= null){
        backgroundSound.stop();
    }
    playBackgroundSound();

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setWidth(1280);
    stage.setHeight(800);
    stage.setResizable(false);
    stage.setTitle("Rat Defender");
    Image ratIcon = new Image(Menu.class.getResourceAsStream("/rat.jpg"));
    stage.getIcons().add(ratIcon);
    stage.show();
//        Scanner scanner = new Scanner(System.in);


    }
    public static MediaPlayer backgroundSound;
    private static Boolean isSoundOn = true;
    private static void playBackgroundSound() {
        String backgroundSoundFile = "mainBGM.mp3";
        URL soundUrl = Menu.class.getClassLoader().getResource(backgroundSoundFile);
        if (soundUrl == null) {
            System.err.println("Failed to load background sound file: " + backgroundSoundFile);
            return;
        }
        try {
            Media media = new Media(soundUrl.toString());
            backgroundSound = new MediaPlayer(media);
            backgroundSound.setOnEndOfMedia(() -> backgroundSound.seek(Duration.ZERO));
            backgroundSound.play();
        } catch (Exception e) {
            System.err.println("Error playing background sound: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static void toggleSound() {
        isSoundOn = !isSoundOn;
        if (isSoundOn) {
            backgroundSound.play();
        } else {
            backgroundSound.stop();
        }
    }

}


