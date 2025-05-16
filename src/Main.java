
import game.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage stage) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        new Menu(stage);
    }

}
