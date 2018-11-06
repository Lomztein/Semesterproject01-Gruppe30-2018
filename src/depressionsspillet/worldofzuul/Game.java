package depressionsspillet.worldofzuul;

import depressionsspillet.worldofzuul.characters.Player;
import depressionsspillet.worldofzuul.interaction.Interaction;
import depressionsspillet.worldofzuul.interaction.Interactable;
import depressionsspillet.worldofzuul.characters.VendorNPC;
import depressionsspillet.worldofzuul.combat.Attack;

public class Game {

    // Instance-variables / attributes for a command parser and a current room is declared for later use.
    private Parser parser;
    private Player player;

    // A few different rooms are quickly declared at once by giving a single Type identifier and a number of variable names afterwards, seperated by commas.
    Room start, magicForrest, vendor, animals, thaiHooker, sleepover, fridayBar, stripClub, kfc, shrek, allotment, movie, drugs, gate, boss, suprise;

    public Game() {
        // The attributes are populated with their appropiate data.
        createRooms();
        player = new Player("Johannes", "Døberen", start);
        parser = new Parser();
    }

    private void createRooms() {

        // The individual room variables are populated with their appropiate Room objects.
        start = new Room("You are standing on a field, under a lonesome tree you see spilmester Martin greeting you with a smile.");
        magicForrest = new Room("You are now in the magicforrest, who knows what will happen.");
        vendor = new Room("You have visited the blackboard vendor, a replacement of blackboard is currently in the works in the meantime please feel free to browse the vendor's wares.");
        animals = new Room("You go deeper into the forrest and find yourself in a completely white room filled with puppies and kittens.");
        thaiHooker = new Room("A beuatiful asian woman approached you and asks what you are doing tonight. She seems interresting but a beautiful woman has never approached you before could it be a trap?");
        sleepover = new Room("You find yourself at your best friends house in your pajamas with icecream. Your best friends invites you inside for a sleepover.");
        fridayBar = new Room("Wuhuu it is friday on SDU and you suddenly feel your spirite soaring and you feel like getting smashed and so you do... You feel great.");
        stripClub = new Room("As you continued through the forrest you notices a couple of flikering light as you move closer you see a sign saying: 'Gentleman's club. Free tonight' You enter, look around, start smiling slyly and have a great time. Your mum would be disappointed");
        kfc = new Room("Suddenly in your path you see a familiar red sign with tree white letters. It reads: KFC, and you are overjoyed. You enter and when you tell the cashier about your amazing journey. She decides to give you free food for your trip and warns you about continuing east becuase a dangerous and mysterious creature lurks in the swamp.");
        shrek = new Room("You defied the warnings of the nice KFC lady and walked onwards to the east. The forest soon ends and a dank swamp emerges. Carefully you explore the area and come across a small wooden shack. As you are about to enter, a rumbling voice appears behinds you ' ' After turning around you realise you have entered the domain of the one and only Shrek. In an adrenaline induced panic you try to escape, but you are easily caught, and as you are dragged inside the raggedy shack, Shreks whispers softly 'it's all ogre now'. The rest of this encounter is best described as a deep scar on your soul, and should never again be spoken off.");
        allotment = new Room("As you continue walking the forest gets brighter as more and more light slips through the treetops. Flowers start to appear and as you follow them you find a small cosy cabin. When you are just about to knock on the door to ask for directions, when you a familiar eerie sound. *heavy forced inhaling* *heavy forced exhaling* you decide to scout out the house for inhabitants, before trying to enter. Walking around to the backyard, you spot a figure dressed in black armor, wearing a black mask and cape, holding a watering can. It is the retired Darth Vader!");
        movie = new Room("You discover a room with a big couch, floffy teddybears and a couple of friends to snuggle. So you dive into the pillows on the couch and rest for a while.");
        drugs = new Room("In a twisted turn of events, you stumble upon a bald russian man selling some sort of homemade white powder. You assume this is heroin, and it is verified by the man with a deep, emotionless 'Da. Krokodil'. You feel slightly eerie and disturbed. ");
        gate = new Room("This is a big impenetrable, unavoidable, indomitable, completely daunting and locked gate. You will need some kind of key to get through.");
        boss = new Room("bossbattle");
        suprise = new Room("In a heroic and almost impossible turn of events you have defeated the despicable Erikthulu/Martin and entered through the last door, behind which all your friends have been watching your valiant fight with eagerness and solemn pride. They all congratulate you on completeing such a feat of strength and cheer you name all the while continually mentioning how proud of you the are, in addition to how much they value your friendship");

        // Exits for are declared.
        start.setExit("south", magicForrest);

        // Exits for magicForrest are declared.
        magicForrest.setExit("south", sleepover);
        magicForrest.setExit("east", vendor);
        magicForrest.setExit("west", thaiHooker);

        // Exits for vendor are declared.
        vendor.setExit("south", stripClub);
        vendor.setExit("east", animals);
        vendor.setExit("west", magicForrest);

        vendor.enterRoom(new VendorNPC("Boris", "A slightly smelly old russian man with a key around his neck.", vendor, false));

        // You know the drill by now.
        animals.setExit("west", vendor);

        thaiHooker.setExit("north", movie);
        thaiHooker.setExit("south", fridayBar);
        thaiHooker.setExit("east", magicForrest);
        thaiHooker.setExit("west", drugs);

        sleepover.setExit("north", magicForrest);
        sleepover.setExit("south", gate);
        sleepover.setExit("east", stripClub);
        sleepover.setExit("west", fridayBar);

        fridayBar.setExit("north", thaiHooker);
        fridayBar.setExit("east", sleepover);

        stripClub.setExit("north", vendor);
        stripClub.setExit("east", kfc);
        stripClub.setExit("west", sleepover);

        kfc.setExit("east", shrek);
        kfc.setExit("west", stripClub);

        shrek.setExit("west", kfc);

        allotment.setExit("south", drugs);
        allotment.setExit("east", movie);

        movie.setExit("south", thaiHooker);
        movie.setExit("west", allotment);

        drugs.setExit("north", allotment);
        drugs.setExit("east", thaiHooker);

        gate.setExit("north", sleepover);
        gate.setExit("south", boss, true);

        boss.setExit("south", suprise);

        // the currentRoom, which represents the room our player is currently in, is assigned the "outside" room.
        // In other words, the game begins with us outside.
    }

    public void play() {
        // Call the printWelcome function, which acts both as a welcome, as well as a simple guide.
        printWelcome();

        // Construct a game loop, which is a simple while-loop that runs until the game is declared "finished".
        boolean finished = false;
        while (!finished) {
            // Use the previously declared parser to find out what command is being input into console.
            Command command = parser.getCommand();

            // If the processed command returns true, then the game ends.
            finished = processCommand(command);
        }

        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        // A simple, warm welcome.
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");

        // A basic guide on how to play this game.
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();

        // An introduction to our current room.
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    private boolean processCommand(Command command) {
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
                    interact(command);
                    break;
                case ATTACK:
                    attack(command);
                    break;
                default:
                    break;
            }
        }
        return wantToQuit;
    }

    private void printHelp() {
        // A desturbingly omnious function for printing out a short guide.
        System.out.println("You are lost. You are alone. You absolute looser.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        // If no second word was given, then ask the player where they need to go.
        if (command.getSecondWord() == null) {
            System.out.println("Go? Go where..?");

            // If this happens, then exit out of this function using a return statement.
            return;
        }

        // If a second word was given, then save that as another variable "direction".
        String direction = command.getSecondWord();

        // Find the next room by finding the appropiate exit in our current room.
        Door nextRoom = player.getCurrentRoom().getExit(direction);

        // If the next room doesn't exist, as in an invalid direction was given, then tell the player that "There is no door!"
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else if (nextRoom.locked == true) {
            nextRoom.locked = false;
            player.setCurrentRoom(nextRoom.getRoom());
            System.out.println(player.getCurrentRoom().getLongDescription());
        } else if (nextRoom.locked == true) {
            System.out.println("This door is locked! It says you need to be happy to enter.");
        } else {
            player.setCurrentRoom(nextRoom.getRoom());
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
        // Otherwise, move to next room and print out the rooms description, so that the player knows where they are.

        /*
                    if(h > 99){
            currentRoom = nextRoom.getRoom();
                System.out.println(currentRoom.getLongDescription());
            } else {
                currentRoom = nextRoom.getRoom();
                System.out.println(currentRoom.getLongDescription());
            }
         */
    }

    private void interact(Command command) {

        Interactable[] interactables = player.getCurrentRoom().getInteractables();
        Interactable correct = null;
        for (Interactable i : interactables) {
            if (i.getName().equals(command.getSecondWord())) {
                correct = i;
            }
        }

        if (correct != null) {
            Interaction interaction = correct.findInteraction(command.getThirdWord());

            if (interaction != null) {
                interaction.execute(player);
            } else {
                System.out.println("You have no idea how to " + command.getSecondWord() + " " + correct.getName());
            }
        } else {
            System.out.println(command.getSecondWord() + " doesn't exists, therefore you cannot interact with it. If this issue persists, you might need medical assistance.");
        }
    }

    private void attack(Command command) {
        if (player.isEngaged()) {
            Attack playerAttack = player.getAttack(command.getSecondWord());
            player.attackEngaged(playerAttack);
        }else {
            System.out.println ("You aren't currently engaged in combat, therefore you cannot attack anything.");
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
}
