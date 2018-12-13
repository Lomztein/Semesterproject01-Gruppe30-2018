package depressionsspillet.ui.graphical;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class FXMLController implements Initializable {

    @FXML
    private Button playGameButton;
    @FXML
    private Button creditsButton;
    private Clip clip;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            File introMusic = new File("music.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(introMusic);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
        }
    }

    @FXML
    private void handlePlayButtonActionEvent(ActionEvent event) throws IOException {
        //stop intro music

        //New scene
        Parent playParent = FXMLLoader.load(getClass().getResource("FXMLPreGame.fxml"));
        Scene playScene = new Scene(playParent);

        //Setting this scene to stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(playScene);
        window.show();
        

    }

    @FXML
    private void handleCreditsButtonActionEvent(ActionEvent event) throws Exception {
        //Stop musik
        clip.stop();

        //New scene
        Parent creditsParent = FXMLLoader.load(getClass().getResource("FXMLCredits.fxml"));
        Scene creditsScene = new Scene(creditsParent);

        //Setting this scene to stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creditsScene);
        window.show();
    }

}
