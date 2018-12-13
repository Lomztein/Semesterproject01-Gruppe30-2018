/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.ui.graphical;

import depressionsspillet.worldofzuul.Game;
import depressionsspillet.worldofzuul.IGame;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joachim
 */
public class FXMLGameController implements Initializable {

    //Interface creation
    IGame game = new Game();

    // for ListViews in game
    ObservableList<String> inventory = FXCollections.observableArrayList();
    ObservableList<String> NPCs = FXCollections.observableArrayList();
    ObservableList<String> items = FXCollections.observableArrayList();
    ObservableList<String> attacks = FXCollections.observableArrayList();
    ObservableList<String> interactions = FXCollections.observableArrayList();
    ObservableList<String> emptyList = FXCollections.observableArrayList();

    //FXML-attributes
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private TextArea txtAreaOutput;
    @FXML
    private Text txfFieldHappiness;
    @FXML
    private ListView<String> lvNPC;
    @FXML
    private ListView<String> lvItems;
    @FXML
    private ListView<String> lvInventory;
    @FXML
    private ListView<String> lvAttacks;
    @FXML
    private ListView<String> lvInteractions;
    @FXML
    private Circle snotface;
    @FXML
    private AnchorPane gamePane;
    @FXML
    private Button attackButton;
    @FXML
    private Button pickUpButton;
    @FXML
    private Button useButton;
    @FXML
    private Button dropButton;
    @FXML
    private Rectangle east;
    @FXML
    private Rectangle west;
    @FXML
    private Rectangle north;
    @FXML
    private Rectangle south;
    @FXML
    private Button interactButton;

    //Attributes
    boolean running, goNorth, goSouth, goEast, goWest;
    //Find a way to make this dynamic
    private static double W = 800, H = 600;
    String[] directionCommands = new String[4];
    Rectangle[] directionObjects = new Rectangle[4];

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image("images/character.png");
        snotface.setFill(new ImagePattern(img));

        //Gets the boundaries of the background image to determine how far X and Y the player can move.
        W = backgroundImageView.getFitWidth();
        H = backgroundImageView.getFitHeight();

        //I was so tired, I figured this was the best I could come up with. Feel free to fix my mess.
        directionCommands[0] = "go north";
        directionCommands[1] = "go east";
        directionCommands[2] = "go west";
        directionCommands[3] = "go south";
        directionObjects[0] = north;
        directionObjects[1] = east;
        directionObjects[2] = west;
        directionObjects[3] = south;

        //Setting starting image
        Image imageStart = new Image("images/start.jpg");
        backgroundImageView.setImage(imageStart);

        //Setting attacks
        attacks.add("dab");
        attacks.add("manifesto");
        lvAttacks.setItems(attacks);

        //Starting the game
        game.playGame();
        txtAreaOutput.setText(game.getCurrentRoomLongDesc());

        //Here comes the logic for handling the movement of the player.
        //This is an animation-timer. It runs the code within the method 'handle', every frame the timer is active, until it is stopped. 
        //This means we can have an active animation running, while the code isn't stuck in a loop, never getting to the end.
        AnimationTimer timer = new AnimationTimer() {

            //This handle checks every frame whether some of the keys are being held, and if they are, sets the movement variable to 1. If they are no longer held, it resets them to zero. 
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
                //We call the movePlayerBy-method every frame
                movePlayerBy(dx, dy);
            }
        };

        //The timer isn't started until we get here, which allows the code to run once first. 
        timer.start();
    }

    //Methods - - - 
    //Determines the rate at which to move the player in X and Y.
    private void movePlayerBy(int dx, int dy) {
        if (dx == 0 && dy == 0) {
            return;
        }

        //Determines the 'movement speed' based on current position.
        double x = getPlayerX() + dx;
        double y = getPlayerY() + dy;

        movePlayerTo(x, y);
    }

    //Determines the X and Y coordinates to move the player to. Makes sure the player can't escape the boundaries of the window's X and Y as well.
    private void movePlayerTo(double x, double y) {

        //I have mildly speaking no clue why cx and cy are neccessary to be the radius. It gets the radius of the player, to determine whether or not the circle is touching the sides, to make sure it wont move any further.
        //However! - If they are changed, the player spirals completely out of control. I have absolutely no idea why. Maybe Im just tired.
        final double cx = getPlayerLocalX() / 2;
        final double cy = getPlayerLocalY() / 2;

        //Determining if the player will move beyond the 'edge' if moved. If yes, don't move.
        if (x - cx >= 0
                && x + cx <= W
                && y - cy >= 0
                && y + cy <= H) {

            //Moves the player to the coordinate.
            movePlayer(x - cx, y - cy);
        }
    }

    //If a key is held, make it's respective boolean value for being held, true.
    @FXML
    private void handleKeyPressed(KeyEvent event) throws IOException {

        switch (event.getCode()) {
            case W:
                goNorth = true;
                break;
            case S:
                goSouth = true;
                break;
            case A:
                goWest = true;
                break;
            case D:
                goEast = true;
                break;
            case SHIFT:
                running = true;
                break;
            case SPACE:
                interact();
                break;
        }

    }

    //When the key is no longer being held, return the value to false.
    @FXML
    private void handleKeyReleased(KeyEvent event) {

        switch (event.getCode()) {
            case W:
                goNorth = false;
                break;
            case S:
                goSouth = false;
                break;
            case A:
                goWest = false;
                break;
            case D:
                goEast = false;
                break;
            case SHIFT:
                running = false;
                break;
        }

    }

    //Gets the width of the object, which is currently a circle - So it gets the diameter.
    private double getPlayerLocalX() {

        return snotface.getLayoutBounds().getWidth();
    }

    //Gets the height of the object, which is currently a circle - So it gets the diameter.
    private double getPlayerLocalY() {

        return snotface.getLayoutBounds().getHeight();
    }

    //Gets the current X-coordinates of the player
    private double getPlayerX() {

        return snotface.getLayoutX();
    }

    //Gets the current Y-coordinates of the player.
    private double getPlayerY() {

        return snotface.getLayoutY();
    }

    //Moves the player to the given X-Y coordinate.
    private void movePlayer(double X, double Y) {
        System.out.println("Moved player to" + X + ", " + Y);
        snotface.relocate(X, Y);
    }

    //Moves the player to another room based on how close they are to it. 
    private void interact() {
        //The idea here is to get the player's coordinates to determine whether or not they are close enough to a certain object to 'interact' with it.
        //For now, it'll handle the updateRoom-functions, to move the player from one room to another.

        //The for-loop determines if we are close enough to a gate to move through it. If we are, call the respective command.
        for (int i = 0; i < 4; i++) {
            double differenceX = Math.abs(getPlayerX() - directionObjects[i].getLayoutX());
            double differenceY = Math.abs(getPlayerY() - directionObjects[i].getLayoutY());

            System.out.println(differenceX + " " + differenceY);
            
            //Determines whether the player is close enough to a 'door' to move through it
            if (differenceX <= 45 && differenceY <= 45) {
                
                //Checks whether the room is the same before and after the method being called, to make sure the player isn't moved if the room hasn't changed.
                String checker = game.getCurrentRoomName();
                game.enterCommand(directionCommands[i]);
                String pChecker = game.getCurrentRoomName();
                if (pChecker != checker) {
                    updateRoom();
                    return;
                }
                return;
            }
        }
        //Perhaps add a timer to prevent the player from spamming the shit out of the button.

    }

    //Moves the player to the opposite side of the room
    private void updatePlayerLocation() {
        String[] lastWords = game.getCommandWords();
        if (lastWords[0].equals("south")) {
            movePlayer(getPlayerX(), 0);
            System.out.println("south");
        }
        if (lastWords[0].equals("north")) {
            movePlayer(getPlayerX(), H - 20);
            System.out.println("north");
        }
        if (lastWords[0].equals("west")) {
            movePlayer(W - 20, getPlayerY());
            System.out.println("west");
        }
        if (lastWords[0].equals("east")) {
            movePlayer(0, getPlayerY());
            System.out.println("east");
        }
    }

    //Selects the quit-screen FXML document and sets the scene to that one.
    @FXML
    protected void handleQuitButtonEvent(ActionEvent event) throws IOException {
        Parent quitParent = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene quitScene = new Scene(quitParent);

        //Setting this scene to stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(quitScene);
        window.show();
    }

    //Update of items and NPCs to be added here
    private void updateRoom() {
        Image image = new Image("images/" + game.getCurrentRoomName() + ".jpg");
        backgroundImageView.setImage(image);
        updateTxtArea();
        updateItemsList();
        updateNPCList();
        updatePlayerLocation();
    }

    //Updates the text-area to have the current output printed.
    private void updateTxtArea() {
        txtAreaOutput.setText(game.getCurrentRoomLongDesc() + "\nYour happiness rises to: " + game.getCurrentHappiness());
        txfFieldHappiness.setText("" + game.getCurrentHappiness());
    }

    //Removes all current items, and updates it based on the current room.
    private void updateItemsList() {
        items.clear();
        String[] itemarray = game.getItemNames();
        if (itemarray.length != 0) {

            for (String string : itemarray) {
                if (items.contains(string) == false) {
                    items.add(string);
                }
            }
            lvItems.setItems(items);
        } else {
            lvItems.setItems(emptyList);
        }
    }

    //Removes all current NPCs and updates them based on the current room.
    private void updateNPCList() {
        NPCs.clear();
        String[] NPCarray = game.getNPCNames();
        if (NPCarray.length != 0) {
            for (String string : NPCarray) {
                if (NPCs.contains(string) == false) {
                    NPCs.add(string);
                }
            }
            lvNPC.setItems(NPCs);
        } else {
            lvNPC.setItems(emptyList);
        }
    }

    //Drops an item from the inventory observable-list, to rhe rooms' list.
    @FXML
    private void handleDropButtonEvent(ActionEvent event) {
        int selectedInventoryItemIndex = lvInventory.getSelectionModel().getSelectedIndex();
        selectedInventoryItemIndex += 1;
        game.enterCommand("inventory drop " + selectedInventoryItemIndex);
        txtAreaOutput.setText(game.getCommandResponse());
        //Refreshing inventory
        inventory.clear();
        String[] inventoryStrings = game.getPlayerInventoryNames();
        inventory.addAll(Arrays.asList(inventoryStrings));
        lvInventory.setItems(inventory);
        updateItemsList();
    }

    //Picks an item up from the selected observable list, and puts it in the player's inventory.
    @FXML
    private void handlePickUpButtonEvent(ActionEvent event) {
        String selectedItem = lvItems.getSelectionModel().getSelectedItem();
        game.enterCommand("inventory pickup " + selectedItem);
        txtAreaOutput.setText(game.getCommandResponse());

        //Refreshing inventory
        inventory.clear();
        String[] inventoryStrings = game.getPlayerInventoryNames();
        inventory.addAll(Arrays.asList(inventoryStrings));
        lvInventory.setItems(inventory);
        updateItemsList();
    }

    //Attacks the selected NPC. Currently attacks with NULL, and doesn't really work for shit.
    @FXML
    private void handleAttackButtonEvent(ActionEvent event) {
        String selectedNPC = lvNPC.getSelectionModel().getSelectedItem();
        String selectedAttack = lvAttacks.getSelectionModel().getSelectedItem();
        game.enterCommand("engage " + selectedNPC);
        game.enterCommand("attack " + selectedAttack);
        txtAreaOutput.setText("You attacked " + selectedNPC + " with a " + selectedAttack + "!\n" + selectedNPC + "now has " + game.getLastAttackedHealth() + " health left!");
        game.enterCommand("disengage");
    }

    //
    @FXML
    private void handleInteractButtonEvent(ActionEvent event) {
        //TODO 
    }

    //Calls the use-method on the item selected in the inventorys' observable-list. 
    @FXML
    private void handleUseButtonEvent(ActionEvent event) {

        int selectedInventoryItemIndex = lvInventory.getSelectionModel().getSelectedIndex();
        selectedInventoryItemIndex += 1;
        game.enterCommand("inventory use " + selectedInventoryItemIndex);
        txtAreaOutput.setText(game.getCommandResponse());

        //Refreshing inventory
        inventory.clear();
        String[] inventoryStrings = game.getPlayerInventoryNames();
        inventory.addAll(Arrays.asList(inventoryStrings));
        lvInventory.setItems(inventory);
        updateItemsList();
    }

    @FXML
    private void handleHelpButtonEvent(ActionEvent event) {
        txtAreaOutput.setText("You really are useless aren't you? "
                + "\nUse W/A/S/D to move about and SPACEBAR to enter into another room. Hold down SHIFT to run. "
                + "\nUse the buttons on either side of the room to interact with the world. "
                + "\nTry not to get killed.");
    }

    @FXML
    private void handleItemsMouseEvent(MouseEvent event) {
        String desc = lvItems.getSelectionModel().getSelectedItem();
        txtAreaOutput.setText(desc);
    }

    @FXML
    private void handleInventoryMouseEvent(MouseEvent event) {
    }

    @FXML
    private void handleNPCListViewMouseEvent(MouseEvent event) {
    }

}
