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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joachim
 */
public class FXMLGameController implements Initializable {
    @FXML
    private Button southButton;
    @FXML
    private Button westButton;
    @FXML
    private Button eastButton;
    @FXML
    private Button northButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleQuitButtonEvent(ActionEvent event) throws IOException{
        Parent quitParent = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene quitScene = new Scene(quitParent);
        
        //Setting this scene to stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(quitScene);
        window.show();
    }

    @FXML
    private void handleSouthActionEvent(ActionEvent event) {
    }

    @FXML
    private void handleWestActionEvent(ActionEvent event) {
    }

    @FXML
    private void handleEastActionEvent(ActionEvent event) {
    }

    @FXML
    private void handleNorthButtonEvent(ActionEvent event) {
    }
    
}
