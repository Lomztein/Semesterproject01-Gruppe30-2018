package depressionsspillet.worldofzuul;

import static depressionsspillet.worldofzuul.RoomList.*;
import depressionsspillet.worldofzuul.combat.DamageResistance;
import depressionsspillet.worldofzuul.characters.HostileNPC;
import depressionsspillet.worldofzuul.characters.NPC;
import depressionsspillet.worldofzuul.characters.Player;
import depressionsspillet.worldofzuul.interaction.Interaction;
import depressionsspillet.worldofzuul.interaction.Interactable;
import depressionsspillet.worldofzuul.combat.Attack;
import depressionsspillet.worldofzuul.combat.Attacker;
import depressionsspillet.worldofzuul.combat.Damagable;
import depressionsspillet.worldofzuul.combat.Damage;
import depressionsspillet.worldofzuul.combat.DamageType;
import depressionsspillet.worldofzuul.combat.Health;

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
        player = new Player("Janus the Magic Midget", "A fucking loser amirite", start);
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

        boolean wantToQuit = false;

        // Find out just which word was used to get this command.
        CommandWord commandWord = command.getCommandWord();

        // If the commmand word hasn't been declared in code..
        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        // A switch-case block the takes care of the diffirent possible command executions.
        if (null != commandWord) {
            switch (commandWord) {
                case HELP:
                    printHelp();
                    break;
                case GO:
                    goRoom(command);
                    break;
                case QUIT:
                    wantToQuit = quit(command);
                    break;
                case INTERACT:
                    //interact(command);
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

    private void printHelp() {
        // A desturbingly omnious function for printing out a short guide.
        System.out.println("You are lost. You are alone. Again... - Really? For Gods sake...");
        System.out.println("You're currently this happy: " + player.getHealth());
        System.out.println("Right. Your options are:");
        parser.showCommands();
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
                    player.setCurrentRoom (nextRoom.getRoom ());
                }
            } else {
                player.setCurrentRoom(nextRoom.getRoom());
                player.getCurrentRoom().setHappiness(0);
            }
        }
    }

    /*private void interact(Command command) {

        if (command.hasSecondWord()) {

            System.out.println("You have the option to interact with the following: ");
            System.out.println(player.getCurrentRoom().listEntities(Interactable.class));

        } else {

            Interactable[] interactables = player.getCurrentRoom().getEntities(Interactable.class);
            Interactable correct = null;
            for (Interactable i : interactables) {
                if (i.getName().toLowerCase().equals(command.getSecondWord().toLowerCase())) {
                    correct = i;
                }
            }

            if (correct != null) {

                if (command.hasThirdWord()) {

                    Interaction interaction = correct.findInteraction(command.getThirdWord());

                    if (interaction != null) {
                        interaction.execute(player);
                        System.out.println(interaction.getDescription());
                    } else {
                        System.out.println("You have no idea how to " + command.getThirdWord() + " " + correct.getName());
                    }

                } else {
                    System.out.println("You have the option of the following interactions: ");
                    System.out.println(correct.listInteractions());
                }

            } else {
                System.out.println(command.getSecondWord() + " doesn't exists, therefore you cannot interact with it. If this issue persists, you might need medical assistance.");
            }

        }

    }*/

    private void engage(Command command) {

        if (player.isEngaged()) {
            System.out.println("You are already engaged in combat with " + player.getEngaged().getName() + ".");
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
            // TODO: Reimplement in CommandLine.java
            System.out.println("You have the option to engage: ");
            System.out.println(player.getCurrentRoom().listEntities(Damagable.class));
        }
    }

    private void disengage(Command command) {
        if (player.isEngaged()) {
            lastCommandResponse = "You poop yourself a little before disengaging " + player.getEngaged().getName() + " before running to a safe distance.";
            player.disengage();
        } else {
            lastCommandResponse = "You aren't currently engaged in combat.";
        }
    }

    private void attack(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("You have the option of the following attacks:");
            System.out.println(player.getAttackList());
        } else if (player.isEngaged()) {
            Attack playerAttack = player.getAttack(command.getSecondWord());
            if (playerAttack != null) {
                player.attackEngaged(playerAttack);
            } else {
                System.out.println("You don't have the ability to attack using " + command.getSecondWord());
            }
        } else {
            System.out.println("You aren't currently engaged in combat, therefore you cannot attack anything.");
        }
    }

    private void inventory(Command command) {

        //Inventory commands
        if (command.hasSecondWord()) {
            if ("drop".equals(command.getSecondWord())) {
                if (command.hasThirdWord()) {
                    player.dropItem(Integer.parseInt(command.getThirdWord()));
                } else {
                    System.out.println("You attempt to drop nothing. You're worried if you looked stupid. \n\nYou did.\n");
                }
            } else if ("use".equals(command.getSecondWord())) {
                if (command.hasThirdWord()) {
                    player.useItem(Integer.parseInt(command.getThirdWord()));
                } else {
                    System.out.println("You stuff a handfull of nothing in your mouth, and chew for a few seconds.\n\nYou feel just as empty inside as before.");
                }
            } else if ("pickup".equals(command.getSecondWord())) {
                if (command.hasThirdWord()) {
                    //Check the room for the item.name, and add it to inventory

                    if (command.hasFourthWord()) {
                        player.addItem(command.getThirdWord(), command.getFourthWord());
                    }

                    //Check the room for the item.name
                    if (player.addItem(command.getThirdWord()) != null) {
                        System.out.println("You pick up the " + command.getThirdWord() + " and then promptly put it back down.");
                    }

                }
            } else {
                //Obligatory player insult if the command is unknown.
                System.out.println("I don't speak depression. Try rephrasing that, without all the sobbing.");
            }
            //If there's no second input, just check your pockets.
        } else {
            System.out.println("You check your pockets: ");
            player.printInventoryList();
        }
    }

    private void no(Command command) {
        Damage last = player.getHealth().getLastDamage();
        switch (command.getSecondWord()) {

            case "u":
                if (last != null && player.isEngaged() && last.getAttacker() == player.getEngaged() && last.getDamageType() == DamageType.MENTAL) {
                    lastCommandResponse = "With raw confidence and sexual provess you \"no u\" " + player.getEngaged().getName() + "'s last attack straight back at them with magnitudes more strength.";
                    Damage retaliation = new Damage(player, player.getEngaged(), DamageType.MENTAL, last.getDamageValue() * 100);
                    retaliation.doDamage();
                }
                break;

            case "me":
                lastCommandResponse = "You realize the loathsome futility of it all, and decide to finally end it right at the spot. You inhale enough air to explode in a majestic display of viscera.";
                Damage selfsplode = new Damage(player, player, DamageType.FIRE, 1337);
                selfsplode.doDamage();
                break;

            case "us":
                lastCommandResponse = "From within you feel an intense burning, as if The Socialist Manifesto spontaniously materializes in your chest cavity. You have become Lenin himself.";
                player.addAttack(new Attack(DamageType.FIRE, 100, "manifesto", "The physical manifastation of socialst pride."));
                break;
        }
    }

    private boolean quit(Command command) {
        // If the command has a second word, become confused.
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
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
        return lastCommandWords;
    }

    @Override
    public String getPlayerTriedEnteringLockedDoorResponse () {
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
        return player.getCurrentRoom ().getEntityNames(NPC.class);
        // In hindsight I realize that this is contains a reference to NPC, therefore an association.
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
        return getPlayerTriedEnteringLockedDoorResponse () != null;
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
        return player.getLastAttackDamageResponse ();
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
        return player.getHealth().getResistanceForType(player.getHealth ().getLastDamage().getDamageType()).getResponse();
    }

    @Override
    public String getCommandResponse() {
        return lastCommandResponse;
    }
}
