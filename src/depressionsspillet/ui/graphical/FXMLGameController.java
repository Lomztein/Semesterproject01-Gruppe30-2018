/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.ui.graphical;

import depressionsspillet.worldofzuul.Game;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joachim
 */
public class FXMLGameController implements Initializable {

    @FXML
    private ImageView backgroundImageView;
    @FXML
    private TextArea txtAreaOutput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Setting starting image
        try{
        Image imageMagicalForest = new Image("images\\magicalForestImage.jpg");
        backgroundImageView.setImage(imageMagicalForest);
        }
        catch(Exception e){
            System.out.println("Magical forest image not found");
        }
        
        //Starting the game
        Game worldOfZuul = new Game ();
        worldOfZuul.play ();
        
    }

    @FXML
    private void handleQuitButtonEvent(ActionEvent event) throws IOException {
        Parent quitParent = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene quitScene = new Scene(quitParent);

        //Setting this scene to stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(quitScene);
        window.show();
    }

}
