
/**
 * Write a description of class Option here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public enum Option{
    GO("go"), 
    QUIT("quit"), 
    HELP("help"), 
    LOOK("look"), 
    EAT("eat"), 
    BACK("back"), 
    TAKE("take"), 
    DROP("drop"), 
    ITEMS("items"), 
    UKNOWN("uknown"),
    DAR("dar");
    
    private String command;
    
    private Option(String command){
        this.command = command;
    }
    
    /**
     * Metodo que devuelve el comando
     */
    public String getCommand(){
        return command;
    }
}

