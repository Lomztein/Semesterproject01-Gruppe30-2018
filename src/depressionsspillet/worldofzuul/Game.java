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
        Room outside, theatre, pub, lab, office;
      
        // The individual room variables are populated with their appropiate Room objects.
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // Exits for are declared.
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        // Exits for the theatre are declared
        theatre.setExit("west", outside);

        // Exits for the pup are declared.
        pub.setExit("east", outside);

        // You know the drill by now.
        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        // the currentRoom, which represents the room our player is currently in, is assigned the "outside" room.
        // In other words, the game begins with us outside.
        currentRoom = outside;
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
