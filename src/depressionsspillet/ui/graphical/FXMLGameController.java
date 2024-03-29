package depressionsspillet.ui.graphical;

import depressionsspillet.worldofzuul.Game;
import depressionsspillet.worldofzuul.IGame;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private int NPCsInList;

    //FXML-attributes
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private TextArea txtAreaOutput;
    @FXML
    private Text txtFieldHappiness;
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
    @FXML
    private Text txtFieldHealth;
    @FXML
    private Text txtFieldName;

    //Attributes
    boolean running, goNorth, goSouth, goEast, goWest;
    //Find a way to make this dynamic
    private static double W = 800, H = 600;
    String[] directionCommands = new String[4];
    Rectangle[] directionObjects = new Rectangle[4];
    @FXML
    private Button secretButton;
    @FXML
    private ImageView secretIV;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Sets the character-image
        Image charImg = new Image("images/character.png");
        snotface.setFill(new ImagePattern(charImg));

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
        lvAttacks.setItems(attacks);

        // Set NPC list view to observe the NPC list
        lvNPC.setItems(NPCs);

        // Set item list view to observe the item list.
        lvItems.setItems(items);

        // Set interactions list view to observe interaction list.
        lvInteractions.setItems(interactions);

        //Starting the game
        txtAreaOutput.setText(game.getCurrentRoomLongDesc());
        updateInformation();

        //Here comes the logic for handling the movement of the player.
        //This is an animation-timer. It runs the code within the method 'handle', every frame the timer is active, until it is stopped. 
        //This means we can have an active animation running, while the code isn't stuck in a loop, never getting to the end.
        AnimationTimer timer = new AnimationTimer() {

            //This handle checks every frame whether some of the keys are being held, and if they are, sets the movement variable to 1. If they are no longer held, it resets them to zero. 
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;
                if (goNorth) {
                    //System.out.println("up");
                    dy -= 2;
                }
                if (goSouth) {
                    //System.out.println("down");
                    dy += 2;
                }
                if (goEast) {
                    //System.out.println("right");
                    dx += 2;
                }
                if (goWest) {
                    //System.out.println("left");
                    dx -= 2;
                }
                if (running) {
                    dx *= 2;
                    dy *= 2;
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
        //System.out.println("Moved player to" + X + ", " + Y);
        if (!game.isPlayerDead()) {
            snotface.relocate(X, Y);
        }
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
                String prev = game.getCurrentRoomName();
                game.enterCommand(directionCommands[i]);
                String post = game.getCurrentRoomName();

                System.out.println(post);

                if (game.getPlayerTriedEnteringLockedDoor()) {
                    txtAreaOutput.setText(game.getPlayerTriedEnteringLockedDoorResponse());
                } else {
                    if (!prev.equals(post)) {
                        updateRoom();
                        updateInformation();
                        return;
                    }
                }
            }
        }
        //Perhaps add a timer to prevent the player from spamming the shit out of the button.
    }

    //Moves the player to the opposite side of the room
    private void updatePlayerLocation() {
        String[] lastWords = game.getCommandWords();
        if (lastWords[0].equals("south")) {
            movePlayer(getPlayerX(), 0);
            //System.out.println("south");
        }
        if (lastWords[0].equals("north")) {
            movePlayer(getPlayerX(), H - getPlayerLocalY());
            //System.out.println("north");
        }
        if (lastWords[0].equals("west")) {
            movePlayer(W - getPlayerLocalX(), getPlayerY());
            //System.out.println("west");
        }
        if (lastWords[0].equals("east")) {
            movePlayer(0, getPlayerY());
            //System.out.println("east");
        }
    }

    //Selects the quit-screen FXML document and sets the scene to that one.
    @FXML
    protected void handleQuitButtonEvent(ActionEvent event) throws IOException {
        quitGame(event);
    }

    private void quitGame(ActionEvent event) throws IOException {
        Parent quitParent = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene quitScene = new Scene(quitParent);

        //Setting this scene to stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(quitScene);
        window.show();
    }

    //Updates player location, resets the background image, and sets it to the one belonging to the room you are in
    private void updateRoom() {
        updatePlayerLocation();
        Image image = null;
        if (game.getCurrentRoomName() == "suprise") { // Special case for "surprise" room.
            image = new Image("images/" + game.getCurrentRoomName() + ".gif");
        } else {
            image = new Image("images/" + game.getCurrentRoomName() + ".jpg");
        }

        backgroundImageView.setImage(image);
        txtAreaOutput.setText(game.getCurrentRoomLongDesc() + "\nYour happiness rises to: " + game.getCurrentHappiness());
    }

    //Updates all the on-screen information
    private void updateInformation() {
        updateItemList();
        updateNPCList();
        updatePlayerStats();
        updateAttackList();
    }

    //Updates the statistics belonging to the player, and also his name, based on his happiness
    private void updatePlayerStats() {
        txtFieldHappiness.setText("" + game.getCurrentHappiness());
        txtFieldHealth.setText("" + game.getPlayerHealth());
        if (game.getCurrentHappiness() < 50) {
            txtFieldName.setText("Loser " + game.getPlayerName());
        } else if (50 < game.getCurrentHappiness() && 100 > game.getCurrentHappiness()) {
            txtFieldName.setText(game.getPlayerName());
        } else if (game.getCurrentHappiness() >= 100) {
            txtFieldName.setText("Warrior " + game.getPlayerName());
        }
    }

    // Resets and updates the list of attacks to reflect any new additions.
    private void updateAttackList() {
        attacks.clear();
        for (String attack : game.getAvailableAttackNames()) {
            attacks.add(attack);
        }
    }

    //Removes all current items, and updates it based on the current room.
    private void updateItemList() {
        items.clear();
        for (String string : game.getItemNames()) {
            items.add(string);
        }
    }

    //Removes all current NPCs and updates them based on the current room.
    private void updateNPCList() {
        NPCs.clear();
        String[] npcNames = game.getNPCNames();
        String[] interactableNames = game.getInteractableNames();

        boolean[] isHostile = game.isNPCHostile();

        NPCsInList = 0;
        for (int j = 0; j < npcNames.length; j++) {
            NPCs.add(npcNames[j] + (isHostile[j] ? " (hostile)" : ""));
            NPCsInList++;
        }

        for (int i = 0; i < interactableNames.length; i++) {
            if (!NPCs.contains(interactableNames[i])) { // NPC's already added shouldn't occur twice.
                NPCs.add(interactableNames[i]);
            }
        }

        lvNPC.setItems(NPCs);
    }

    //Drops an item from the inventory observable-list, to rhe rooms' list.
    @FXML
    private void handleDropButtonEvent(ActionEvent event
    ) {
        int selectedInventoryItemIndex = lvInventory.getSelectionModel().getSelectedIndex();
        selectedInventoryItemIndex += 1;
        game.enterCommand("inventory drop " + selectedInventoryItemIndex);
        txtAreaOutput.setText(game.getCommandResponse());
        //Refreshing inventory
        inventory.clear();
        String[] inventoryStrings = game.getPlayerInventoryNames();
        inventory.addAll(Arrays.asList(inventoryStrings));
        lvInventory.setItems(inventory);
        updateItemList();
    }

    //Picks an item up from the selected observable list, and puts it in the player's inventory.
    @FXML
    private void handlePickUpButtonEvent(ActionEvent event
    ) {
        String selectedItem = lvItems.getSelectionModel().getSelectedItem();
        game.enterCommand("inventory pickup " + selectedItem);
        txtAreaOutput.setText(game.getCommandResponse());

        //Refreshing inventory
        inventory.clear();
        String[] inventoryStrings = game.getPlayerInventoryNames();
        inventory.addAll(Arrays.asList(inventoryStrings));
        lvInventory.setItems(inventory);
        updateItemList();
    }

    //Attacks the selected NPC. Currently attacks with NULL, and doesn't really work for shit.
    @FXML
    private void handleAttackButtonEvent(ActionEvent event
    ) {
        String selectedNPC = lvNPC.getSelectionModel().getSelectedItem();
        String selectedAttack = lvAttacks.getSelectionModel().getSelectedItem();
        game.enterCommand("engage " + selectedNPC);

        if (game.getEngagedName() != null) {

            game.enterCommand("attack " + selectedAttack);

            String attackString = "Your attack failed. Just like you did.\n";
            String retaliationString = game.getEngagedName() + " just takes it.";

            if (game.getLastAttackDidHit()) {
                attackString = getAttackString(
                        game.getPlayerName(),
                        game.getEngagedName(),
                        game.getLastAttackName(),
                        game.getLastAttackDescription(),
                        game.getLastAttackResponse(),
                        game.getLastAttackType(),
                        game.getLastAttackDamage(),
                        game.getLastAttackedHealth()
                );
            }

            if (game.getLastAttackHadRetaliation()) {
                retaliationString = getAttackString(
                        game.getEngagedName(),
                        game.getPlayerName(),
                        game.getRetaliationAttackName(),
                        game.getRetaliationAttackDescription(),
                        game.getRetaliationAttackResponse(),
                        game.getRetaliationAttackType(),
                        game.getRetaliationAttackDamage(),
                        game.getPlayerHealth()
                );
            } else if (game.getLastAttackedHealth() <= 0) {
                retaliationString += " " + game.getEngagedName() + " is dead. You're a murderer now.";
            }

            txtAreaOutput.setText(attackString + retaliationString);
        } else {

            if (selectedAttack.toUpperCase().equals("DAB")) {
                txtAreaOutput.setText("You dab fruitlessly at nothing, as faint yeeting flows through the forest trees.");
            } else {
                txtAreaOutput.setText("You attack the air with " + selectedAttack + ". It takes great offence, but nothing otherwise happens.");
            }

        }

        game.enterCommand("disengage");
        updateInformation();

        try {
            checkDeath(event); // Apparently the event is needed to change scene, and I can't be bothered to look for better solutions right now. Please forgive.
        } catch (IOException ex) {
            Logger.getLogger(FXMLGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void checkDeath(ActionEvent event) throws IOException {
        if (game.isPlayerDead()) {
            txtAreaOutput.setText(txtAreaOutput.getText() + "\nIt appears you've run out of lifejuice. Congratulations, you're a failure");
        }
    }

    private String getAttackString(String attacker, String reciever, String name, String description, String response, String type, double value, double remaining) {
        String result = "";
        result += (String.format(attacker + " attacks " + reciever + " with " + name + ", " + description + " doing " + type + " damage.", value)) + "\n";
        result += (String.format(reciever + " " + response, value)) + "\n";
        result += (reciever + " has " + remaining + " health left .") + "\n";
        return result;
    }

    //Handles the interact-button based on what you have selected in the interactable-observablelist
    @FXML
    private void handleInteractButtonEvent(ActionEvent event) {
        game.enterCommand("interact " + lvNPC.getSelectionModel().getSelectedItem() + " " + lvInteractions.getSelectionModel().getSelectedItem());
        txtFieldHappiness.setText("" + game.getCurrentHappiness());
        txtFieldHealth.setText("" + game.getPlayerHealth());
        txtAreaOutput.setText(game.getCommandResponse());
        updateInformation();
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
        updateInformation();
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
        int index = lvItems.getSelectionModel().getSelectedIndex();
        String description = game.getItemDescriptions()[index];
        txtAreaOutput.setText(description);
    }

    @FXML
    private void handleInventoryMouseEvent(MouseEvent event) {
        int index = lvInventory.getSelectionModel().getSelectedIndex();
        String description = game.getPlayerInventoryDescriptions()[index];
        txtAreaOutput.setText(description);
    }

    @FXML
    private void handleAttackListViewMouseEvent(MouseEvent event) {
        int index = lvAttacks.getSelectionModel().getSelectedIndex();
        String description = game.getAvailableAttackDescriptions()[index];
        txtAreaOutput.setText(description);
    }

    @FXML
    private void handleInteractionsListViewMouseEvent(MouseEvent event) {
        System.out.println(selectedInteractable);
        if (selectedInteractable != -1) {
            int index = lvInteractions.getSelectionModel().getSelectedIndex();
            String description = game.getInteractionDescriptions()[selectedInteractable][index];
            txtAreaOutput.setText(description);
        }
    }

    //Prinitng interactions for chosen NPC in listview
    @FXML
    private void handleNPCListViewMouseEvent(MouseEvent event) {

        int index = lvNPC.getSelectionModel().getSelectedIndex();
        boolean isNPC = index < game.getNPCNames().length;

        String description = null;
        if (isNPC) {
            if (index > -1) { // Seems as if missing a specific slot yet still hitting the box calls this, so just to make stop exceptions from happening.
                description = game.getNPCDescriptions()[index];
            }
        } else {
            index -= NPCsInList;
            description = game.getInteractableDescriptions()[index];
        }

        txtAreaOutput.setText(description);

        updateInteractions();
    }

    //Handles what is shown in the observable list based on which character or interactable you have selected
    private int selectedInteractable = -1;

    private void updateInteractions() {
        interactions.clear();
        selectedInteractable = getInteractableIndex(game.getInteractableNames(), lvNPC.getSelectionModel().getSelectedItem());

        if (selectedInteractable != -1) {
            String[][] npcInteractions = game.getInteractionNames();
            for (String interaction : npcInteractions[selectedInteractable]) {
                interactions.add(interaction);
            }
        }
    }

    //Gets the index of the array in which the interactions are placed.
    private int getInteractableIndex(String[] interactables, String interactable) {
        String[] names = game.getInteractableNames();
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            if (name.toUpperCase().equals(interactable.toUpperCase())) {
                return i;
            }
        }
        return -1;
    }

    //You're a nosy little bastard, huh? I'm not telling you what it does.
    @FXML
    private void handleSecretButton(ActionEvent event) {
        secretAction();
    }

    //The secret button of secrets. Hit me if you dare.
    private void secretAction() {

        game.enterCommand("no me");
        Image secretGIF = new Image("images/character.gif");
        secretIV.setImage(secretGIF);
        secretIV.relocate(getPlayerX() - (secretIV.getLayoutBounds().getWidth() / 2), getPlayerY() - (secretIV.getLayoutBounds().getHeight() / 2));
        snotface.opacityProperty().setValue(0);
        txtAreaOutput.setText(game.getCommandResponse());

    }

}
