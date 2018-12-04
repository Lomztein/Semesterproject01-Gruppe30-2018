/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.ui.graphical;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lomztein
 */
public class FXMLController implements Initializable {
    @FXML
    private Button playGameButton;
    @FXML
    private Button creditsButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handlePlayButtonActionEvent(ActionEvent event) throws IOException {
         //New scene
        Parent playParent = FXMLLoader.load(getClass().getResource("FXMLGame.fxml"));
        Scene playScene = new Scene(playParent);
        
        //Setting this scene to stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(playScene);
        window.show();
    }

    @FXML
    private void handleCreditsButtonActionEvent(ActionEvent event) throws Exception {
        //New scene
        Parent creditsParent = FXMLLoader.load(getClass().getResource("FXMLCredits.fxml"));
        Scene creditsScene = new Scene(creditsParent);
        
        //Setting this scene to stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(creditsScene);
        window.show();
    }
    
}
