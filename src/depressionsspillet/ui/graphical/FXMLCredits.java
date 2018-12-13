package depressionsspillet.ui.graphical;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FXMLCredits implements Initializable {
    @FXML
    private GridPane gridPaneCredits;
    @FXML
    private Label creditLabel;
    @FXML
    private ImageView imageViewCredits;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleBackButtonActionEvent(ActionEvent event) throws Exception{
        //New scene
        Parent backParent = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene creditsScene = new Scene(backParent);
        
        //Setting this scene to stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(creditsScene);
        window.setFullScreen(true);
        window.show();
    }
    
}
