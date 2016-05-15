import java.util.ArrayList;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private ArrayList<Option> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
        validCommands = new ArrayList<>();
        validCommands.add(Option.GO);
        validCommands.add(Option.QUIT);
        validCommands.add(Option.HELP);
        validCommands.add(Option.LOOK);
        validCommands.add(Option.EAT);
        validCommands.add(Option.BACK);
        validCommands.add(Option.TAKE);
        validCommands.add(Option.DROP);
        validCommands.add(Option.ITEMS);
        validCommands.add(Option.UKNOWN);
         validCommands.add(Option.DAR);
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        boolean encontrado = false;
        int i = 0;
        while(i < validCommands.size() && !encontrado){
            if(validCommands.get(i).getCommand().equals(aString)){
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll(){
        String comandosDisponibles = "Lista de comandos disponibles: ";
        for(Option comando: validCommands){
            comandosDisponibles += comando.getCommand() + " ";
        }
        System.out.println(comandosDisponibles);
    }

    /**
     * Return the object Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return the object Option correspondng to the paramater commandWord, or the object Option.UNKNOWN
     *         if it is not a valid command word
     */
    public Option getCommandWord(String commandWord){
        Option opt = null;
        int i = 0;
        boolean encontrado = false;
        while(i < validCommands.size() && !encontrado){
            if(validCommands.get(i).getCommand().equals(commandWord)){
                encontrado = true;
                opt = validCommands.get(i);
            }
            i++;
        }
        if(!encontrado) {
            opt = Option.UKNOWN;
        }
        return opt;
    }
}
