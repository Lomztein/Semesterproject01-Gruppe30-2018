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
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joachim
 */
public class FXMLGameController implements Initializable {

    //Interface
    IGame game = new Game();

    // for ListViews in game
    ObservableList<String> inventory = FXCollections.observableArrayList();
    ObservableList<String> NPCs = FXCollections.observableArrayList();
    ObservableList<String> items = FXCollections.observableArrayList();
    ObservableList<String> emptyList = FXCollections.observableArrayList();

    @FXML
    private ImageView backgroundImageView;
    @FXML
    private TextArea txtAreaOutput;
    @FXML
    private Button goWestButton;
    @FXML
    private Button northButton;
    @FXML
    private Button goEastButton;
    @FXML
    private Button goSouthButton;
    @FXML
    private Text txfFieldHappiness;
    @FXML
    private Label selectedLabel;
    @FXML
    private Button attackButton;
    @FXML
    private Button useButton;
    @FXML
    private Button dropButton;
    @FXML
    private Button pickUpButton;
    @FXML
    private ToggleGroup attackToggleGroup;
    @FXML
    private ListView<String> lvNPC;
    @FXML
    private ListView<String> lvItems;
    @FXML
    private ListView<String> lvInventory;
    //<Placeholder>
    @FXML
    private Circle snotface;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Setting starting image
        Image imageMagicalForest = new Image("images\\spilMesterMartinImage.jpg");
        backgroundImageView.setImage(imageMagicalForest);

        game.playGame();
        txtAreaOutput.setText(game.getCurrentRoomLongDesc());

    }

    @FXML
    protected void handleQuitButtonEvent(ActionEvent event) throws IOException {
        Parent quitParent = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene quitScene = new Scene(quitParent);

        //Setting this scene to stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(quitScene);
        window.show();
    }

    //Update of items and NPC's to be added here
    private void updateRoom() {
        switch (game.getCurrentRoomName()) {
            case "magicForrest":
                //Setting new image
                Image image = new Image("images\\magicalForestImage.jpg");
                backgroundImageView.setImage(image);
                break;
            case "vendor":
                Image image2 = new Image("images\\vendorImage.jpg");
                backgroundImageView.setImage(image2);
                break;
            case "animals":
                Image image3 = new Image("images\\animalsImage.jpg");
                backgroundImageView.setImage(image3);
                break;
            case "thaiHooker":
                Image image4 = new Image("images\\thaiHookerImage.jpg");
                backgroundImageView.setImage(image4);
                break;
            case "sleepover":
                Image image5 = new Image("images\\sleepOverImage2.jpg");
                backgroundImageView.setImage(image5);
                break;
            case "fridayBar":
                Image image6 = new Image("images\\fridayBarImage.jpg");
                backgroundImageView.setImage(image6);
                break;
            case "stripClub":
                Image image7 = new Image("images\\stripClubImage.jpg");
                backgroundImageView.setImage(image7);
                break;
            case "kfc":
                Image image8 = new Image("images\\kfcImage.jpg");
                backgroundImageView.setImage(image8);
                break;
            case "shrek":
                Image image9 = new Image("images\\shrekImage.jpg");
                backgroundImageView.setImage(image9);
                break;
            case "allotment":
                Image image10 = new Image("images\\allotmentImage.jpg");
                backgroundImageView.setImage(image10);
                break;
            case "movie":
                Image image11 = new Image("images\\movieImage.jpg");
                backgroundImageView.setImage(image11);
                break;
            case "drugs":
                Image image12 = new Image("images\\drugsImage.jpg");
                backgroundImageView.setImage(image12);
                break;
            case "gate":
                Image image13 = new Image("images\\gateImage.jpg");
                backgroundImageView.setImage(image13);
                break;
            case "boss":
                Image image14 = new Image("images\\bossImage.jpg");
                backgroundImageView.setImage(image14);
                break;
            case "suprise":
                Image image15 = new Image("images\\surpriseImage.jpg");
                backgroundImageView.setImage(image15);
                break;
            default:
                txtAreaOutput.setText("You cannot go this way. Try another :)");
        }
        updateTxtArea();
        updateItemsList();
        updateNPCList();
    }

    private void updateTxtArea() {
        txtAreaOutput.setText(game.getCurrentRoomLongDesc() + "\nYour happiness rises by: " + game.getRoomHappiness());
        txfFieldHappiness.setText("" + game.getCurrentHappiness());
    }

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

    @FXML
    private void handleGoWestButtonEvent(ActionEvent event
    ) {
        game.enterCommand("go west");
        updateRoom();
    }

    @FXML
    private void handleGoNorthButtonEvent(ActionEvent event
    ) {
        game.enterCommand("go north");
        updateRoom();
    }

    @FXML
    private void handleGoEastButtonEvent(ActionEvent event
    ) {
        game.enterCommand("go east");
        updateRoom();
    }

    @FXML
    private void handleGoSouthButtonEvent(ActionEvent event
    ) {
        game.enterCommand("go south");
        updateRoom();
    }

    @FXML
    private void handleAttackButtonEvent(ActionEvent event) {
    }

    @FXML
    private void handleUseButtonEvent(ActionEvent event) {
    }

    @FXML
    private void handleDropButtonEvent(ActionEvent event) {
        int selectedInventoryItemIndex = lvInventory.getSelectionModel().getSelectedIndex();
        selectedInventoryItemIndex += 1;
        game.enterCommand("inventory drop " + selectedInventoryItemIndex);

        //Refreshing inventory
        inventory.clear();
        String[] inventoryStrings = game.getPlayerInventoryNames();
        for (String string : inventoryStrings) {
            inventory.add(string);
        }
        lvInventory.setItems(inventory);
        updateItemsList();
    }

    @FXML
    private void handlePickUpButtonEvent(ActionEvent event) {
        String selectedItem = lvItems.getSelectionModel().getSelectedItem();
        game.enterCommand("inventory pickup " + selectedItem);
        //Refreshing inventory
        String[] inventoryStrings = game.getPlayerInventoryNames();
        for (String string : inventoryStrings) {
            if (inventory.contains(string) == false) {
                inventory.add(string);
            }
        }
        lvInventory.setItems(inventory);
        updateItemsList();
    }

    //Gets the width of the object, which is currently a circle - So it gets the diameter.
    public double getPlayerLocalX() {

        return snotface.getLayoutBounds().getWidth();
    }

    //Gets the height of the object, which is currently a circle - So it gets the diameter.
    public double getPlayerLocalY() {

        return snotface.getLayoutBounds().getHeight();
    }

    //Gets the current X-coordinates of the player
    public double getPlayerX() {

        return snotface.getLayoutX();
    }

    //Gets the current Y-coordinates of the player.
    public double getPlayerY() {

        return snotface.getLayoutY();
    }

    //Moves the player to the given X-Y coordinate.
    public void movePlayer(double X, double Y) {
        System.out.println("Moved player to" + X + ", " + Y);
        snotface.relocate(X, Y);
    }

}
