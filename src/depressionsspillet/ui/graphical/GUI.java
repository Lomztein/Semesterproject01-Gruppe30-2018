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
    private static final double W = 1024, H = 768;

    //To access the methods in the FXML-controller, this dank-ass workaround had to be implemented.
    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGame.fxml"));
    FXMLGameController control;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Depressionsspillet!");

        //Loading the FXML-document as root, and then getting the controller from it.
        Parent root = loader.load();
        control = (FXMLGameController) loader.getController();

        Scene scene = new Scene(root, 1024, 768);

        //Sets the respective keys' value to 'true', which is used by the animationtimerhandle.
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP:
                    goNorth = true;
                    break;
                case DOWN:
                    goSouth = true;
                    break;
                case LEFT:
                    goWest = true;
                    break;
                case RIGHT:
                    goEast = true;
                    break;
                case SHIFT:
                    running = true;
                    break;
            }
        });

        //Sets the values false, if the key is released.
        scene.setOnKeyReleased((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP:
                    goNorth = false;
                    break;
                case DOWN:
                    goSouth = false;
                    break;
                case LEFT:
                    goWest = false;
                    break;
                case RIGHT:
                    goEast = false;
                    break;
                case SHIFT:
                    running = false;
                    break;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

        //This is an animation-timer. It runs the code within the method 'handle', every frame the timer is active, until it is stopped. 
        //This means we can have an active animation running, while the code isn't stuck in a loop, never getting to the end.
        AnimationTimer timer = new AnimationTimer() {

            //This handle checks every frame whether some of the keys are being held, and if they are, sets the movement variable to 1. If they are not held, it resets them to zero. 
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;
                if (goNorth) {
                    System.out.println("up");
                    dy -= 1;
                }
                if (goSouth) {
                    System.out.println("down");
                    dy += 1;
                }
                if (goEast) {
                    System.out.println("right");
                    dx += 1;
                }
                if (goWest) {
                    System.out.println("left");
                    dx -= 1;
                }
                if (running) {
                    dx *= 3;
                    dy *= 3;
                }
                movePlayerBy(dx, dy);
            }
        };

        //The timer isn't started until we get here, which allows the code to run once first. 
        timer.start();
    }

    //Determines the rate at which to move the player in X and Y.
    private void movePlayerBy(int dx, int dy) {
        if (dx == 0 && dy == 0) {
            return;
        }

        //Determines the 'movement speed' based on current position.
        double x = control.getPlayerX() + dx;
        double y = control.getPlayerY() + dy;

        movePlayerTo(x, y);
    }

    //Determines the X and Y coordinates to move the player to. Makes sure the player can't escape the boundaries of the window's X and Y as well.
    private void movePlayerTo(double x, double y) {

        //I have mildly speaking no clue why cx and cy are neccessary to be the radius. It gets the radius of the player, to determine whether or not the circle is touching the sides, to make sure it wont move any further.
        //However! - If they are changed, the player spirals completely out of control. I have absolutely no idea why. Maybe Im just tired.
        final double cx = control.getPlayerLocalX() / 2;
        final double cy = control.getPlayerLocalY() / 2;

        //Determining if the player will move beyond the 'edge' if moved. If yes, don't move.
        if (x - cx >= 0
                && x + cx <= W
                && y - cy >= 0
                && y + cy <= H) {

            //Moves the player to the coordinate.
            control.movePlayer(x - cx, y - cy);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
