package game;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class WinScreen {

        private Stage stage;
        public WinScreen(Stage stage){
            this.stage = stage;
            buildUI(stage);
        }
        private void buildUI(Stage stage){
            StackPane root = new StackPane();
            Button menuButton = new Button();
            Text menuText = new Text("Main Menu");
            menuText.setFont(Font.font("Inter", FontWeight.BOLD,55));
            menuText.setFill(Color.WHITE);

            menuButton.setGraphic(menuText);
            menuButton.setBackground(new Background(new BackgroundFill(Color.web("0x217663", 1.0),null,null)));

            menuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        new Menu(stage);
                    } catch (UnsupportedAudioFileException e) {
                        throw new RuntimeException(e);
                    } catch (LineUnavailableException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            menuButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    menuButton.setBackground(new Background(new BackgroundFill(Color.web("0x155043", 1.0),null,null)));
                }
            });
            menuButton.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    menuButton.setBackground(new Background(new BackgroundFill(Color.web("0x217663", 1.0),null,null)));
                }
            });



            Image winBackground = new Image(WinScreen.class.getResourceAsStream("/winBackground.jpg"));
            ImageView backgroundView = new ImageView(winBackground);
            backgroundView.setViewport(new Rectangle2D(0,0,1152,720));
            backgroundView.setFitHeight(800);
            backgroundView.setFitWidth(1280);

            root.getChildren().add(backgroundView);

            Text youWon = new Text("You Won");
            youWon.setFont(Font.font("Inter", FontWeight.BOLD,75));
            youWon.setFill(Color.WHITE);
            youWon.setStrokeWidth(3);
            youWon.setStroke(Color.web("0x234627", 1.0));
            VBox textBox = new VBox();
            textBox.getChildren().add(youWon);
            textBox.setAlignment(Pos.TOP_CENTER);
            root.getChildren().add(textBox);
            root.getChildren().add(menuButton);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setWidth(1280);
            stage.setHeight(800);
            stage.show();
        }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

