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
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
import java.util.Stack;

public class Game 
{
    private Parser parser;
    private Player jugador;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        jugador = new Player(5);
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

        // añadimos items a las calles
        atasco.addItem(new Item("Pistola", 1.2F, true));
        cruce.addItem(new Item("Helado", 0.07F, true));
        parking.addItem(new Item("Bate", 1.5F, false));
        noTrafico.addItem(new Item("Gasolina", 15.0F, false));
        cortada.addItem(new Item("GPS", 1.7F, true));
        lenta.addItem(new Item("Escopeta", 5.3F, true));
        noSemaforo.addItem(new Item("Periodico", 0.5F, true));
        obras.addItem(new Item("Lanzacohetes", 13.0F, false));
        trabajo.addItem(new Item("Billetes", 0.2F, false));

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

        jugador.fijarCalle(atasco);
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
        System.out.println("Type " +  Option.HELP.getCommand() + " if you need help.");
        System.out.println();
        jugador.printLocationInfo();
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
        Option commandWord = command.getCommandWord();
        switch(commandWord){
            case HELP:
            printHelp();
            break;

            case GO:
            jugador.goRoom(command);
            break;
            
            case QUIT:
            wantToQuit = quit(command);
            break;
            
            case LOOK:
            jugador.printLocationInfo();
            break;
            
            case EAT:
            System.out.println("You have eaten now and you are not hungry any more");
            break;
            
            case BACK:
            jugador.goToLastRoom();
            break;
            
            case TAKE:
            jugador.takeItem(command.getSecondWord());
            break;
            
            case DROP:
            jugador.dropItem(command.getSecondWord());
            break;
            
            case ITEMS:
            jugador.showItems();
            break;

            default:
            System.out.println("I don't know what you mean...");
            return false;

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

}
