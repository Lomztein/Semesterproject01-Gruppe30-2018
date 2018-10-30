package depressionsspillet.worldofzuul;

public class Game 
{
    // Instance-variables / attributes for a command parser and a current room is declared for later use.
    private Parser parser;
    private Room currentRoom;
        

    public Game() 
    {
        // The attributes are populated with their appropiate data.
        createRooms();
        parser = new Parser();
    }


    private void createRooms()
    {
        // A few different rooms are quickly declared at once by giving a single Type identifier and a number of variable names afterwards, seperated by commas.
        Room start, magicForrest, vendor, animals, thaiHooker, sleepover, fridayBar, stripClub, kfc, shrek, allotment, movie, drugs, gate, boss, suprise;
      
        // The individual room variables are populated with their appropiate Room objects.
        start = new Room("The intro room");
        magicForrest = new Room("forrest");
        vendor = new Room("forrest");
        animals = new Room("forrest");
        thaiHooker = new Room("forrest");
        sleepover = new Room("forrest");
        fridayBar = new Room("forrest");
        stripClub = new Room("forrest");
        kfc = new Room("forrest");
        shrek = new Room("forrest");
        allotment = new Room("forrest");
        movie = new Room("forrest");
        drugs = new Room("forrest");
        gate = new Room("forrest");
        boss = new Room("forrest");
        suprise = new Room("forrest");
        
        // Exits for are declared.
        start.setExit("south", magicForrest);
        
        // Exits for magicForrest are declared.
        magicForrest.setExit("west", thaiHooker);
        magicForrest.setExit("south", sleepover);
        magicForrest.setExit("east", vendor);

        // Exits for vendor are declared.
        vendor.setExit("south", stripClub);
        vendor.setExit("east", animals);
        vendor.setExit("west", magicForrest);

        // You know the drill by now.
        animals.setExit("west", vendor);

        thaiHooker.setExit("north", movie);
        thaiHooker.setExit("west", drugs);
        thaiHooker.setExit("south", fridayBar);
        thaiHooker.setExit("east", magicForrest);
        
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
        gate.setExit("south", boss);
        
        boss.setExit("south", suprise);

        // the currentRoom, which represents the room our player is currently in, is assigned the "outside" room.
        // In other words, the game begins with us outside.
        currentRoom = start;
    }

    public void play() 
    {            
        // Call the printWelcome function, which acts both as a welcome, as well as a simple guide.
        printWelcome();
                
        // Construct a game loop, which is a simple while-loop that runs until the game is declared "finished".
        boolean finished = false;
        while (! finished) {
            // Use the previously declared parser to find out what command is being input into console.
            Command command = parser.getCommand();
            
            // If the processed command returns true, then the game ends.
            finished = processCommand(command);
        }
        
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        // A simple, warm welcome.
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        
        // A basic guide on how to play this game.
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        
        // An introduction to our current room.
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        // Find out just which word was used to get this command.
        CommandWord commandWord = command.getCommandWord();

        // If the commmand word hasn't been declared in code..
        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        // A switch-case block the takes care of the diffirent possible command executions.
        if (null != commandWord) switch (commandWord) {
            case HELP:
                printHelp();
                break;
            case GO:
                goRoom(command);
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
            default:
                break;
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        // A desturbingly omnious function for printing out a short guide.
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        // If no second word was given, then ask the player where they need to go.
        if(command.getSecondWord () == null) {
            System.out.println("Go where?");
            
            // If this happens, then exit out of this function using a return statement.
            return;
        }

        // If a second word was given, then save that as another variable "direction".
        String direction = command.getSecondWord();

        // Find the next room by finding the appropiate exit in our current room.
        Room nextRoom = currentRoom.getExit(direction);

        // If the next room doesn't exist, as in an invalid direction was given, then tell the player that "There is no door!"
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            // Otherwise, move to next room and print out the rooms description, so that the player knows where they are.
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) 
    {
        // If the command has a second word, become confused.
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            // If not, return true, which then quits the game through the previously mentioned "wantToQuit" boolean variable on line 87.
            return true;
        }
    }
}
