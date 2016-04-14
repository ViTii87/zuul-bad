/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room atasco, cruce, parking, noTrafico, cortada, lenta, noSemaforo, obras, trabajo;

        // create the rooms
        atasco = new Room("en una calle atascada");
        cruce = new Room("en un cruce bastante rapido");
        parking = new Room("en un parking");
        noTrafico = new Room("en una calle sin trafico");
        cortada = new Room("en una calle cortada");
        lenta = new Room("en una calle lenta");
        noSemaforo = new Room("en una calle sin semaforos");
        obras = new Room("en una calle en obras");
        trabajo = new Room("en la calle del trabajo");

        // initialise room exits
        atasco.setExit("este", cruce);
        atasco.setExit("sureste", parking);
        parking.setExit("norte", cruce);
        parking.setExit("noroeste", atasco);
        cruce.setExit("norte", lenta);
        cruce.setExit("este", noTrafico);
        cruce.setExit("sur", parking);
        cruce.setExit("oeste", atasco);
        noTrafico.setExit("norte", noSemaforo);
        noTrafico.setExit("este", cortada);
        noTrafico.setExit("oeste", cruce);
        cortada.setExit("oeste", noTrafico);
        lenta.setExit("norte", obras);
        lenta.setExit("este", noSemaforo);
        lenta.setExit("sur", cruce);
        noSemaforo.setExit("norte", trabajo);
        noSemaforo.setExit("sur", noTrafico);
        noSemaforo.setExit("oeste", lenta);
        obras.setExit("este", trabajo);
        obras.setExit("sur", lenta);
        trabajo.setExit("oeste", obras);
        trabajo.setExit("sur", noSemaforo);

        currentRoom = atasco;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            printLocationInfo();
        }
        else if (commandWord.equals("eat")) {
            System.out.println("You have eaten now and you are not hungry any more");
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        parser.printAllCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("A donde vamos?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("No hay salida!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
            System.out.println();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quitar que?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Metodo que imprime la informacion de localizacion
     */
    private void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
    }
}
