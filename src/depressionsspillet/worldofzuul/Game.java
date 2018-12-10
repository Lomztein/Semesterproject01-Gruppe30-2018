package depressionsspillet.worldofzuul;

import static depressionsspillet.worldofzuul.RoomList.*;
import depressionsspillet.worldofzuul.characters.NPC;
import depressionsspillet.worldofzuul.characters.Player;
import depressionsspillet.worldofzuul.combat.Attack;
import depressionsspillet.worldofzuul.combat.Damagable;
import depressionsspillet.worldofzuul.combat.Damage;
import depressionsspillet.worldofzuul.combat.DamageType;
import java.util.ArrayList;

public class Game implements IGame {

    // Instance-variables / attributes for a command parser and a current room is declared for later use.
    private Parser parser;
    private Player player;

    // Keep track of whether or not you tried to enter a locked room.
    private String triedEnteringLockedRoomResponse;

    // Keep track of which command was last input.
    private String lastCommand;
    private String[] lastCommandWords;

    // Track otherwise unavailable textual responses to commands.
    private String lastCommandResponse;

    public Game() {
        // The attributes are populated with their appropiate data.
        createRooms();
        player = new Player("Janus the Magic Midget", "A fucking loser amirite", RoomList.start);
        player.addAttack(new Attack(DamageType.DAB, 5, "dab", "a profound dab"));
        player.addAttack(new Attack(DamageType.BLUNT, 20, "punch", "a rather weak, yet beautifully spirited punch"));
        parser = new Parser();
    }

    private void createRooms() {
        RoomList.listRooms();
    }

    private boolean processCommand(Command command) {

        lastCommand = command.getCommandWord().name();
        lastCommandWords = new String[]{
            command.getSecondWord(),
            command.getThirdWord(),
            command.getFourthWord(),};
        lastCommandResponse = null;
        
        boolean wantToQuit = false;

        // Find out just which word was used to get this command.
        CommandWord commandWord = command.getCommandWord();

        // If the commmand word hasn't been declared in code..
        if (commandWord == CommandWord.UNKNOWN) {
            return false;
        }

        // A switch-case block the takes care of the diffirent possible command executions.
        if (null != commandWord) {
            switch (commandWord) {
                case GO:
                    goRoom(command);
                    break;
                case ATTACK:
                    attack(command);
                    break;
                case ENGAGE:
                    engage(command);
                    break;
                case DISENGAGE:
                    disengage(command);
                    break;
                case INVENTORY:
                    inventory(command);
                    break;
                case NO:
                    no(command);
                    break;
                default:
                    break;
            }
        }
        return wantToQuit;
    }

    private void goRoom(Command command) {
        if (command.getSecondWord() == null) {
            return;
        }

        // If a second word was given, then save that as another variable "direction".
        String direction = command.getSecondWord();

        // Find the next room by finding the appropiate exit in our current room.
        Door nextRoom = player.getCurrentRoom().getExit(direction);

        // If the next room doesn't exist, as in an invalid direction was given, then tell the player that "There is no door!"
        triedEnteringLockedRoomResponse = null;
        if (nextRoom != null) {
            if (nextRoom.isLocked()) {
                if (player.getHappiness() < 99d) {
                    triedEnteringLockedRoomResponse = "You quiver in fear at the sight of this mighty gate, as you lack the self-comfidence to enter. Return when you are happier.";
                } else {
                    player.setCurrentRoom(nextRoom.getRoom());
                }
            } else {
                player.setCurrentRoom(nextRoom.getRoom());
                player.addHappiness(player.getCurrentRoom().getHappiness());
                player.getCurrentRoom().setHappiness(0);
            }
        }
    }

    //Engage replaces the interact-command.
    //This allows the player to 'engage' with an NPC, opening up the command 'attack'. 
    private void engage(Command command) {

        if (player.isEngaged()) {
            lastCommandResponse = ("You are already engaged in combat with " + player.getEngaged().getName() + ".");
        }

        if (command.hasSecondWord()) {
            Damagable toEngage = player.getCurrentRoom().getEntity(Damagable.class, command.getSecondWord());
            if (toEngage != null) {
                player.engage(toEngage);
                lastCommandResponse = "You engage " + toEngage.getName() + " with spirit and vigor!";
            } else {
                lastCommandResponse = "There is no " + command.hasSecondWord() + " that you can engage.";
            }
        } else {
            // TODO: Reimplement in CommandLine.java <------- 
            lastCommandResponse = ("You have the iption to engage: " + player.getCurrentRoom().listEntities(Damagable.class));
        }
    }

    //Disengages the player with the NPC, if he is engaged.
    private void disengage(Command command) {
        if (player.isEngaged()) {
            lastCommandResponse = "You accidentally poop yourself a little before disengaging " + player.getEngaged().getName() + " before running to a safe distance.";
            player.disengage();
        } else {
            lastCommandResponse = "You aren't currently engaged in combat.";
        }
    }

    private void attack(Command command) {
        if (!command.hasSecondWord()) {
            lastCommandResponse = ("You have the option of the following attacks: " + player.getAttackList());
        } else if (player.isEngaged()) {
            Attack playerAttack = player.getAttack(command.getSecondWord());
            if (playerAttack != null) {
                player.attackEngaged(playerAttack);
            } else {
                lastCommandResponse = ("You don't have the ability to attack using " + command.getSecondWord());
            }
        } else {
            lastCommandResponse = ("You aren't currently engaged in combat, therefore you cannot attack anything.");
        }
    }

    private void inventory(Command command) {

        //Inventory commands
        if (command.hasSecondWord()) {
            if (null == command.getSecondWord()) {
                //Obligatory player insult if the command is unknown.
                lastCommandResponse = ("I don't speak depression. Try rephrasing that, without all the sobbing.");
            } else {
                switch (command.getSecondWord()) {
                    case "drop":
                        if (command.hasThirdWord()) {
                            player.dropItem(Integer.parseInt(command.getThirdWord()));
                        } else {
                            lastCommandResponse = ("You attempt to drop nothing. You're worried if you looked stupid. \n\nYou did.\n");
                        }
                        break;
                    //If there's no second input, just check your pockets.
                    case "use":
                        if (command.hasThirdWord()) {
                            try {
                                int temp = Integer.parseInt(command.getThirdWord());
                                lastCommandResponse = player.useItem(temp);
                            } catch (NumberFormatException exc) {
                                lastCommandResponse = ("That's not a pocket number you massive tosser.");
                            }
                        } else {
                            lastCommandResponse = ("You stuff a handfull of nothing in your mouth, and chew for a few seconds.\n\nYou feel just as empty inside as before.");
                        }
                        break;
                    case "pickup":
                        if (command.hasThirdWord()) {

                            //Check the room for the item.name, and add it to inventory
                            lastCommandResponse = player.addItem(command.getThirdWord());
                        }
                        break;
                    default:
                        //Obligatory player insult if the command is unknown.
                        lastCommandResponse = ("I don't speak depression. Try rephrasing that, without all the sobbing.");
                        break;
                }
            }
        } else {
            lastCommandResponse = ("You check your pockets:\n" + player.printInventoryList());
        }
    }

    //Inside joke, that only works in the CLI version
    private void no(Command command) {
        Damage last = player.getHealth().getLastDamage();
        if (command.hasSecondWord()) {
            switch (command.getSecondWord()) {

                case "u":
                    if (last != null && player.isEngaged() && last.getAttacker() == player.getEngaged() && last.getDamageType() == DamageType.MENTAL) {
                        lastCommandResponse = "With raw confidence and sexual provess you \"no u\" " + player.getEngaged().getName() + "'s last attack straight back at them with magnitudes more strength.";
                        Damage retaliation = new Damage(player, player.getEngaged(), DamageType.MENTAL, last.getDamageValue() * 100);
                        retaliation.doDamage();
                    }
                    break;

                case "me":
                    lastCommandResponse = "You realize the loathsome futility of it all, and decide to finally end it right on the spot. You inhale enough air to explode in a majestic display of viscera.";
                    Damage selfsplode = new Damage(player, player, DamageType.FIRE, 1337);
                    selfsplode.doDamage();
                    break;

                case "us":
                    lastCommandResponse = "From within you feel an intense burning, as if The Socialist Manifesto spontaniously materializes in your chest cavity. You have become Lenin himself.";
                    player.addAttack(new Attack(DamageType.FIRE, 100, "manifesto", "The physical manifastation of socialst pride."));
                    break;
            }
        } else {
            lastCommandResponse = "You fool..!";
        }
    }

    private boolean quit(Command command) {
        // If the command has a second word, become confused.
        if (command.hasSecondWord()) {
            lastCommandResponse = ("Quit what?");
            return false;
        } else {
            // If not, return true, which then quits the game through the previously mentioned "wantToQuit" boolean variable on line 87.
            return true;
        }
    }

    //Overrides from interface, primary goal is to be used in GUI
    @Override
    public boolean enterCommand(String input) {
        Command cmd = parser.getCommand(input);
        return processCommand(cmd);
    }

    @Override
    public String[] getCurrentExits() {
        return player.getCurrentRoom().getExitNames();
    }

    @Override
    public String getCurrentRoomShortDesc() {
        return player.getCurrentRoom().getShortDescription();
    }

    @Override
    public String getCurrentRoomLongDesc() {
        return player.getCurrentRoom().getLongDescription();
    }

    @Override
    public String getLastCommand() {
        return lastCommand;
    }

    @Override
    public String[] getCommandWords() {
        ArrayList<String> tempArray = parser.showCommands();
        String[] stringArray = tempArray.toArray(new String[tempArray.size()]);

        return stringArray;
    }

    @Override
    public String getPlayerTriedEnteringLockedDoorResponse() {
        return this.triedEnteringLockedRoomResponse;
    }

    @Override
    public double getPlayerHealth() {
        return player.getHealth().getCurrentHealth();
    }

    @Override
    public String[] getItemNames() {
        return player.getCurrentRoom().getItemNames();
    }

    @Override
    public String[] getItemDescriptions() {
        return player.getCurrentRoom().getItemDescriptions();
    }

    @Override
    public String[] getNPCNames() {
        return player.getCurrentRoom().getEntityNames(NPC.class);
        // In hindsight I realize that this contains a reference to NPC, therefore an association.
        // We should reconsider this generic approach. Perhaps implement it differently.
    }

    @Override
    public String[] getNPCDescriptions() {
        return player.getCurrentRoom().getEntityDescriptions(NPC.class);
    }

    @Override
    public void playGame() {
        createRooms();
    }

    @Override
    public int getRoomHappiness() {
        return player.getCurrentRoom().getHappiness();
    }

    @Override
    public int getCurrentHappiness() {
        return player.getHappiness();
    }

    @Override
    public boolean[] getLockedExitsFlags() {
        return player.getCurrentRoom().getIsExitLockedFlags();
    }

    @Override
    public boolean getPlayerTriedEnteringLockedDoor() {
        return getPlayerTriedEnteringLockedDoorResponse() != null;
    }

    @Override
    public String[] getPlayerInventoryNames() {
        return player.getInventoryItemNames();
    }

    @Override
    public String[] getPlayerInventoryDescriptions() {
        return player.getInventoryItemDescriptions();
    }

    @Override
    public String[] getAvailableAttackNames() {
        return player.getAttackNames();
    }

    @Override
    public String[] getAvailableAttackDescriptions() {
        return player.getAttackDescriptions();
    }

    @Override
    public double getLastAttackDamage() {
        return player.getLastAttackDamageValue();
    }

    @Override
    public String getLastAttackResponse() {
        return player.getLastAttackDamageResponse();
    }

    @Override
    public double getLastAttackedHealth() {
        return player.getLastAttackedHealth();
    }

    @Override
    public String getCurrentRoomName() {
        return player.getCurrentRoom().getShortDescription();
    }

    @Override
    public double getRetaliationAttackDamage() {
        return player.getHealth().getLastDamage().getDamageValue();
    }

    @Override
    public String getRetaliationAttackResponse() {
        return player.getHealth().getResistanceForType(player.getHealth().getLastDamage().getDamageType()).getResponse();
    }

    @Override
    public String getCommandResponse() {
        return lastCommandResponse;
    }

    @Override
    public String[] getAvailableCommands() {
        return parser.getCommands();
    }
}
