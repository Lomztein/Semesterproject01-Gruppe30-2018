/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.ui.graphical;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Lomztein
 */
public class GUI extends Application {

    //Attributes
    boolean running, goNorth, goSouth, goEast, goWest;

    //This should be dynmaic, but currently isn't. It's the expected size of the screen for the player to stay within.
    private static final double W = 456, H = 285;

    //To access the methods in the FXML-controller, this dank-ass workaround had to be implemented.
    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml"));
    FXMLController control;

    @Override
    public void start(Stage primaryStage) throws Exception {

        //THIS NEEDS TO BE REVERTED TO JUST START THE MAIN MENU INSTEAD
        
        primaryStage.setTitle("Depressionsspillet!");

        //Loading the FXML-document as root, and then getting the controller from it.
        Parent root = loader.load();

        Scene scene = new Scene(root, 1024, 768);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaximized(true);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
